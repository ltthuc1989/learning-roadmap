package com.example.review

import kotlinx.coroutines.delay
import java.io.IOException
import java.net.SocketTimeoutException
import java.util.UUID

class HttpResponse(val status: Int, val body: Any?)

class HttpException(val status: Int, message: String) : IOException(message)

interface HttpClient {
    suspend fun get(path: String, headers: Map<String, String> = emptyMap()): HttpResponse
    suspend fun post(path: String, body: Any?, headers: Map<String, String> = emptyMap()): HttpResponse
}

/** Retries [block] until it succeeds, doubling the delay between attempts. */
suspend fun <T> retry(initialDelayMillis: Long = 200L, block: suspend () -> T): T {
    var delayMillis = initialDelayMillis
    var attempt = 0
    while (true) {
        try {
            return block()
        } catch (e: Exception) {
            attempt++
            Log.w("Retry", "attempt $attempt failed: ${e.message}")
            delay(delayMillis)
            delayMillis *= 2
        }
    }
}

/**
 * In-memory fake of the backend so the project runs without a network.
 * Transaction pages are zero-based: page=0 returns the first [size] items.
 */
class InMemoryHttpClient(
    private val transactions: List<Map<String, Any>>,
) : HttpClient {

    var transferCount: Int = 0
        private set

    /** Number of upcoming POST /transfers calls that will time out after the server applied them. */
    var failNextTransferTimes: Int = 0

    val requestLog: MutableList<String> = ArrayList()

    override suspend fun get(path: String, headers: Map<String, String>): HttpResponse {
        requestLog.add("GET $path")
        if (!path.contains("/transactions")) return HttpResponse(404, null)
        val query = queryOf(path)
        val page = query["page"]?.toIntOrNull() ?: 0
        val size = query["size"]?.toIntOrNull() ?: 20
        val from = page * size
        val items = if (from >= transactions.size) {
            emptyList()
        } else {
            transactions.subList(from, minOf(from + size, transactions.size))
        }
        return HttpResponse(200, mapOf("items" to items, "total" to transactions.size, "page" to page))
    }

    override suspend fun post(path: String, body: Any?, headers: Map<String, String>): HttpResponse {
        requestLog.add("POST $path")
        return when {
            path == "/auth/refresh" -> HttpResponse(
                200,
                mapOf(
                    "accessToken" to "tok-${UUID.randomUUID()}",
                    "expiresAt" to System.currentTimeMillis() / 1000 + 3600,
                ),
            )
            path == "/transfers" -> {
                transferCount++
                if (failNextTransferTimes > 0) {
                    failNextTransferTimes--
                    throw SocketTimeoutException("read timed out")
                }
                HttpResponse(200, mapOf("transferId" to "tr-$transferCount"))
            }
            path.endsWith("/sync") -> HttpResponse(200, mapOf("ok" to true))
            else -> HttpResponse(404, null)
        }
    }

    private fun queryOf(path: String): Map<String, String> {
        val query = path.substringAfter('?', "")
        if (query.isEmpty()) return emptyMap()
        return query.split('&').mapNotNull { pair ->
            val kv = pair.split('=')
            if (kv.size == 2) kv[0] to kv[1] else null
        }.toMap()
    }
}
