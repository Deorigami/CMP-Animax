package com.eyedea.shared_core.util

import android.content.Context
import android.util.Log
import com.chuckerteam.chucker.api.ChuckerInterceptor
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.statement.bodyAsText
import io.ktor.client.utils.HttpResponseReceived

class AppContext(val context: Context)

actual class KtorClientEngine constructor(private val appContext: AppContext) {
    actual fun getClientEngine(config: HttpClientConfig<*>.() -> Unit) : HttpClient = HttpClient(OkHttp) {
        config.invoke(this)
        engine {
//            requestTimeout = 0
            addInterceptor(ChuckerInterceptor.Builder(appContext.context).build())

        }
    }
    actual companion object Factory {
        lateinit var context: Context
        actual fun build(): KtorClientEngine = KtorClientEngine(AppContext(context))
    }
}

