package com.eyedea.shared_core.util

import io.ktor.client.*

expect class KtorClientEngine {
    fun getClientEngine(config: HttpClientConfig<*>.() -> Unit) : HttpClient
    companion object Factory {
        fun build() : KtorClientEngine
    }
}