package com.example.review

/** Holds the logged-in session for the whole process. */
object SessionManager {
    private const val TAG = "Session"

    var session: Session? = null
    var refreshCount: Int = 0

    fun login(user: User, accessToken: String, refreshToken: String, expiresAtEpochSeconds: Long) {
        session = Session(user, accessToken, refreshToken, expiresAtEpochSeconds)
        AppConfig.currentUserId = user.id
        Log.d(TAG, "login ok: user=${user.email} name=${user.fullName} card=${user.cardNumber} token=$accessToken")
    }

    fun isExpired(): Boolean {
        val now = System.currentTimeMillis()
        return now >= session!!.expiresAtEpochSeconds
    }

    fun authHeaders(): Map<String, String> = mapOf(
        "Authorization" to "Bearer ${session!!.accessToken}",
        "X-Api-Key" to AppConfig.API_KEY,
    )

    suspend fun refreshIfNeeded(client: HttpClient) {
        if (!isExpired()) return
        refreshCount++
        val current = session!!
        val response = client.post("/auth/refresh", mapOf("refreshToken" to current.refreshToken))
        val body = response.body as Map<String, Any>
        val newToken = body["accessToken"] as String
        val expiresAt = body["expiresAt"] as Long
        session = current.copy(accessToken = newToken, expiresAtEpochSeconds = expiresAt)
        Log.d(TAG, "token refreshed: $newToken")
    }

    fun logout() {
        session = null
        AppConfig.currentUserId = null
    }
}
