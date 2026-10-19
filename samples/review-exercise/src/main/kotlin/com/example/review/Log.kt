package com.example.review

/** Minimal stand-in for android.util.Log so the project has no Android dependency. */
object Log {
    fun d(tag: String, message: String) {
        if (AppConfig.debugLogging) println("D/$tag: $message")
    }

    fun w(tag: String, message: String) {
        println("W/$tag: $message")
    }

    fun e(tag: String, message: String, t: Throwable? = null) {
        val cause = if (t != null) " (${t::class.simpleName}: ${t.message})" else ""
        println("E/$tag: $message$cause")
    }
}
