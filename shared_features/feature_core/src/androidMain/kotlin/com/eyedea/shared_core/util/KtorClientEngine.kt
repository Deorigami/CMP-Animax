package com.eyedea.shared_core.util

import android.content.Context
import android.util.Log
import com.chuckerteam.chucker.api.ChuckerInterceptor
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.cache.storage.FileStorage
import io.ktor.client.statement.bodyAsText
import io.ktor.client.utils.HttpResponseReceived
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

class AppContext(val context: Context)

actual class KtorClientEngine constructor(private val appContext: AppContext) {
    actual fun getClientEngine(config: HttpClientConfig<*>.() -> Unit) : HttpClient = HttpClient(OkHttp) {
        config.invoke(this)
        engine {
//            requestTimeout = 0
            addInterceptor(ChuckerInterceptor.Builder(appContext.context).build())

        }
        install(HttpCache){
//            val cacheFile = Files.createDirectory(Paths.get("build/cache")).toFile()
//            publicStorage(FileStorage(cacheFile))
        }
    }
    actual companion object Factory {
        lateinit var context: Context
        actual fun build(): KtorClientEngine = KtorClientEngine(AppContext(context))
    }
}

