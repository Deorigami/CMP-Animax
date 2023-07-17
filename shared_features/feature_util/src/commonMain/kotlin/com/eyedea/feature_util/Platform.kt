package com.eyedea.feature_util

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform