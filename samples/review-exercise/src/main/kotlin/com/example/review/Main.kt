package com.example.review

import kotlinx.coroutines.runBlocking
import java.io.File

/** Runs a short scenario against the in-memory backend. Compare the output with what you expect. */
fun main() = runBlocking {
    val serverData = (0 until 45).map { i ->
        mapOf<String, Any>(
            "id" to "tx-$i",
            "accountId" to "acc-1",
            "amountCents" to (i + 1) * 1000L,
            "currency" to "VND",
            "description" to "Payment #$i",
            "createdAt" to 1_700_000_000_000L + i * 60_000L,
        )
    }
    val client = InMemoryHttpClient(serverData)
    val cache = Cache<PageKey, List<Transaction>>(ttlMillis = 60_000L)
    val repository = TransactionRepository(client, cache, File(System.getProperty("java.io.tmpdir")))

    SessionManager.login(
        user = User("u-1", "alice@example.com", "Alice Nguyen", "4111 1111 1111 1111"),
        accessToken = "tok-initial",
        refreshToken = "rt-initial",
        expiresAtEpochSeconds = System.currentTimeMillis() / 1000 + 3600,
    )

    val first = repository.loadNextPage("acc-1")
    val second = repository.loadNextPage("acc-1")
    println("First page ids:  ${first.map { it.id }}")
    println("Second page ids: ${second.map { it.id }}")
    println("Server has ${serverData.size} transactions. Shown: ${first.size + second.size}")
    println("Token refresh count after 2 loads: ${SessionManager.refreshCount}")
    println("Cache hits=${cache.hits} misses=${cache.misses}")

    client.failNextTransferTimes = 1
    val transferId = repository.createTransfer(TransferRequest("acc-1", "acc-2", 50_000L, "rent"))
    println("Transfer returned $transferId; server applied ${client.transferCount} transfer(s)")
    println("Requests: ${client.requestLog}")
    println("Open ANSWERS.md only after your 45 minutes are up.")
}
