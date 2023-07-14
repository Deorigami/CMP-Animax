package com.eyedea.shared_core.util

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.darwin.Darwin

actual class KtorClientEngine {
    actual fun getClientEngine(config: HttpClientConfig<*>.() -> Unit) : HttpClient = HttpClient(Darwin){
        engine {
            configureRequest {
                setAllowsCellularAccess(true)
            }
        }
    }
    actual companion object Factory {
        actual fun build() : KtorClientEngine = KtorClientEngine()
    }
}