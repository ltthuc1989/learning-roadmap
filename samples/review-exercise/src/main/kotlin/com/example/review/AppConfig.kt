package com.example.review

/** Global configuration shared by every layer of the app. */
object AppConfig {
    const val API_KEY = "FAKE-LIVE-API-KEY-for-review-exercise-9f3a7c1e"
    const val BASIC_AUTH = "svc-mobile:P@ssw0rd-2026"

    var baseUrl: String = "https://api.example.com"
    var pageSize: Int = 20
    var debugLogging: Boolean = true
    var currentUserId: String? = null
}
