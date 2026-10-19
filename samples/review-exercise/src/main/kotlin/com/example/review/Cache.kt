package com.example.review

import java.io.File

/** Simple TTL cache shared by repositories. */
class Cache<K, V>(private val ttlMillis: Long) {

    private class Entry<V>(val value: V, val storedAtMillis: Long)

    private val map = HashMap<K, Entry<V>>()

    var hits: Int = 0
        private set
    var misses: Int = 0
        private set

    fun get(key: K): V? {
        val entry = map[key]
        if (entry == null) {
            misses++
            return null
        }
        if (System.currentTimeMillis() - entry.storedAtMillis > ttlMillis) {
            map.remove(key)
            misses++
            return null
        }
        hits++
        return entry.value
    }

    fun put(key: K, value: V) {
        map[key] = Entry(value, System.currentTimeMillis())
    }

    fun evictExpired() {
        val now = System.currentTimeMillis()
        for (key in map.keys) {
            val entry = map[key] ?: continue
            if (now - entry.storedAtMillis > ttlMillis) {
                map.remove(key)
            }
        }
    }

    fun size(): Int = map.size

    fun persist(file: File) {
        try {
            val text = map.entries.joinToString("\n") { "${it.key}=${it.value.value}" }
            file.writeText(text)
        } catch (e: Exception) {
            // best effort, ignore
        }
    }
}
