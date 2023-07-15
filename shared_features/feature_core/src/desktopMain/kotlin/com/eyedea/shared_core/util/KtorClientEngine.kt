package com.eyedea.shared_core.util

import io.github.aakira.napier.log
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.apache.Apache

actual class KtorClientEngine {
    actual fun getClientEngine(config: HttpClientConfig<*>.() -> Unit) : HttpClient = HttpClient(Apache) {
        config.invoke(this)
        engine {

        }
    }
    actual companion object Factory {
        actual fun build(): KtorClientEngine = KtorClientEngine()
    }
}

