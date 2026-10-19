package com.example.review

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File

class TransactionRepositoryTest {

    private lateinit var client: InMemoryHttpClient
    private lateinit var repository: TransactionRepository

    @BeforeEach
    fun setUp(@TempDir tempDir: File) {
        val data = (0 until 45).map { i ->
            mapOf<String, Any>(
                "id" to "tx-$i",
                "accountId" to "acc-1",
                "amountCents" to (i + 1) * 1000L,
                "currency" to "VND",
                "description" to "Payment #$i",
                "createdAt" to 1_700_000_000_000L + i * 60_000L,
            )
        }
        client = InMemoryHttpClient(data)
        repository = TransactionRepository(client, Cache(ttlMillis = 60_000L), tempDir)
        SessionManager.login(
            user = User("u-1", "alice@example.com", "Alice", "4111 1111 1111 1111"),
            accessToken = "tok",
            refreshToken = "rt",
            expiresAtEpochSeconds = System.currentTimeMillis() / 1000 + 3600,
        )
    }

    @Test
    fun `loadNextPage returns a full page`() = runTest {
        val items = repository.loadNextPage("acc-1")
        assertEquals(AppConfig.pageSize, items.size)
    }

    @Test
    fun `createTransfer succeeds when the first attempt times out`() = runTest {
        client.failNextTransferTimes = 1
        repository.createTransfer(TransferRequest("acc-1", "acc-2", 1_000L, "test"))
    }
}
