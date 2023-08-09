package com.eyedea.shared_core.util

import android.annotation.SuppressLint
import android.content.Context
import java.io.File
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@SuppressLint("StaticFieldLeak")
actual object CacheUtil {

    lateinit var context: Context

    private fun encodeFileName(fileName: String): String {
        return URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString())
    }

    actual fun saveHttpResponse(name: String, body: String) {
        val encodedFileName = encodeFileName(name)
        val file = File(context.filesDir, encodedFileName)
        file.writeText(body)
    }

    actual fun getCachedHttpResponse(name: String): String? {
        val encodedFileName = encodeFileName(name)
        val file = File(context.filesDir, encodedFileName)
        return if (file.exists()) {
            file.readText()
        } else {
            null
        }
    }


}