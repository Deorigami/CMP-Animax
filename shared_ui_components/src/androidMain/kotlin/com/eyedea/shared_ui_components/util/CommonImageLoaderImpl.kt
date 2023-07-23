package com.eyedea.shared_ui_components.util

import android.content.Context
import com.seiko.imageloader.component.ComponentRegistryBuilder
import com.seiko.imageloader.component.setupAndroidComponents
import com.seiko.imageloader.component.setupCommonComponents
import com.seiko.imageloader.component.setupDefaultComponents
import com.seiko.imageloader.component.setupKtorComponents
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import okhttp3.Dns
import okhttp3.OkHttpClient
import okio.Path
import okio.Path.Companion.toPath
import java.net.Inet4Address
import java.net.InetAddress

actual object CommonImageLoaderImpl : CommonImageLoader {

    lateinit var applicationContext : Context

    override fun ComponentRegistryBuilder.setupDefaultComponents() {
        val client = OkHttpClient.Builder()
            .dns(HostsDns(mapOf(
                "i.animepahe.com" to "https://dns.adguard-dns.com/"
            )))
            .build()
       this.setupDefaultComponents(applicationContext) { HttpClient(OkHttp){
           engine {
//               preconfigured = client
           }
       } }
    }

    override fun getImageCacheDirectoryPath(): Path {
        return applicationContext.cacheDir.absolutePath.toPath()
    }
}

class HostsDns(private val map: Map<String, String>) : Dns {
    override fun lookup(hostname: String): List<InetAddress> {
        Napier.d { "HostDnsHostName : $hostname" }
        val ip = map[hostname]
        return if (ip != null) listOf(Inet4Address.getByName(ip)) else Dns.SYSTEM.lookup(hostname)
    }
}