package com.eyedea.shared_core.util

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object CacheUtil {
    fun saveHttpResponse(name: String, body: String)
    fun getCachedHttpResponse(name: String) : String?
}