package com.eyedea.feature_detail

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform