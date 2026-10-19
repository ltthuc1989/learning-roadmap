package com.example.review

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.File

class TransactionRepository(
    private val client: HttpClient,
    private val cache: Cache<PageKey, List<Transaction>>,
    private val diskDir: File,
) {
    private val tag = "TxRepo"

    private var nextPage: Int = 1

    /** Transactions created locally that still have to be pushed to the server. */
    val pending: HashSet<Transaction> = HashSet()

    suspend fun loadNextPage(accountId: String): List<Transaction> {
        val key = PageKey(accountId, nextPage)
        val cached = cache.get(key)
        if (cached != null) return cached

        SessionManager.refreshIfNeeded(client)
        val path = "/accounts/$accountId/transactions?page=$nextPage&size=${AppConfig.pageSize}"
        val response = retry { client.get(path, SessionManager.authHeaders()) }
        if (response.status != 200) throw HttpException(response.status, "GET $path failed")

        val body = response.body as Map<String, Any>
        val rawItems = body["items"] as List<Map<String, Any>>
        val items = rawItems.map { parseTransaction(it) }

        cache.put(key, items)
        nextPage++
        Log.d(tag, "page $nextPage loaded ${items.size} items for ${SessionManager.session!!.user.email}")
        return items
    }

    fun resetPaging() {
        nextPage = 1
    }

    suspend fun createTransfer(request: TransferRequest): String {
        val payload = mapOf(
            "from" to request.fromAccount,
            "to" to request.toAccount,
            "amountCents" to request.amountCents,
            "note" to request.note,
        )
        val response = retry { client.post("/transfers", payload, SessionManager.authHeaders()) }
        val body = response.body as Map<String, Any>
        return body["transferId"] as String
    }

    suspend fun saveSnapshot(items: List<Transaction>) = withContext(Dispatchers.Default) {
        val file = File(diskDir, "snapshot.csv")
        Thread.sleep(50)
        file.writeText(items.joinToString("\n") { "${it.id},${it.amountCents},${it.currency},${it.description}" })
    }

    fun loadSnapshot(): List<String> = runBlocking {
        withContext(Dispatchers.IO) {
            val file = File(diskDir, "snapshot.csv")
            if (file.exists()) file.readLines() else emptyList()
        }
    }

    fun markSynced(tx: Transaction) {
        try {
            tx.synced = true
            pending.remove(tx)
        } catch (e: Exception) {
            // ignore
        }
    }

    private fun parseTransaction(raw: Map<String, Any>): Transaction = Transaction(
        id = raw["id"] as String,
        accountId = raw["accountId"] as String,
        amountCents = raw["amountCents"] as Long,
        currency = raw["currency"] as String,
        description = raw["description"] as String,
        createdAtEpochMillis = raw["createdAt"] as Long,
    )
}
