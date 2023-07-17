package com.eyedea.shared_core.base

import com.eyedea.shared_core.getPlatform
import com.eyedea.shared_core.util.KtorClientEngine
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.url
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.Platform
import kotlinx.serialization.json.Json

object KtorNetworkProvider {

    val platform = getPlatform().name
    val client get() = KtorClientEngine.build().getClientEngine {
        defaultRequest {
            url("https://api.jikan.moe/v4/")
            headers {

            }
        }
        install(HttpTimeout){
            requestTimeoutMillis = HttpTimeout.INFINITE_TIMEOUT_MS
            socketTimeoutMillis = HttpTimeout.INFINITE_TIMEOUT_MS
            connectTimeoutMillis = HttpTimeout.INFINITE_TIMEOUT_MS
        }
        install(ResponseObserver){
            onResponse {

            }
        }
        install(ContentNegotiation){
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }
}

suspend inline fun <reified T> clientGet(
    url: String,
    requestBuilder: (HttpRequestBuilder) -> Unit = {}
) : BaseRespondDto<T> = KtorNetworkProvider.client.get{
    url(url)
    requestBuilder.invoke(this)
}.body()