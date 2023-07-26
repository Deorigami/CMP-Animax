package com.eyedea.shared_core.util

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.plugins.defaultRequest
import platform.Foundation.NSTimeInterval

actual class KtorClientEngine {
    actual fun getClientEngine(config: HttpClientConfig<*>.() -> Unit) : HttpClient = HttpClient(Darwin){
        defaultRequest {
            url(SessionManager.BASE_URL)
        }
        engine {
            configureRequest {
                setTimeoutInterval(NSTimeInterval.fromBits(30))
                setAllowsCellularAccess(true)
            }
        }
    }
    actual companion object Factory {
        actual fun build() : KtorClientEngine = KtorClientEngine()
    }
}