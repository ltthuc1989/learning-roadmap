package com.example.review

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/** Pushes locally created transactions to the server in the background. */
class SyncService(
    private val repository: TransactionRepository,
    private val client: HttpClient,
) {
    private val tag = "Sync"

    private val scope = CoroutineScope(Dispatchers.IO)

    val queue: ArrayList<Transaction> = ArrayList()

    var lastSyncAtMillis: Long = 0L

    fun start(intervalMillis: Long = 5_000L) {
        scope.launch {
            while (true) {
                try {
                    drainQueue()
                    lastSyncAtMillis = System.currentTimeMillis()
                    delay(intervalMillis)
                } catch (t: Throwable) {
                    Log.e(tag, "sync loop failed, continuing", t)
                }
            }
        }
    }

    fun enqueue(tx: Transaction) {
        queue.add(tx)
        repository.pending.add(tx)
    }

    suspend fun drainQueue() {
        while (queue.isNotEmpty()) {
            val tx = queue.removeAt(0)
            retry {
                client.post(
                    "/transactions/${tx.id}/sync",
                    mapOf("accountId" to tx.accountId, "amountCents" to tx.amountCents),
                )
            }
            repository.markSynced(tx)
        }
    }
}
