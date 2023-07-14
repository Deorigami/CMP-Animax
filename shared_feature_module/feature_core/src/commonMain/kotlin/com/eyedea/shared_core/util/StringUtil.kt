package com.eyedea.shared_core.util

object StringUtil {
    fun transformPhoneNumber(input: String): String {
        return kotlin.runCatching {
            val prefix = input.substring(0, 4)
            val maskedNumber = "XXXX-XXXX"

            "$prefix-$maskedNumber"
        }.getOrElse { input }
    }
}