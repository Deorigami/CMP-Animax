package com.eyedea.service_anime

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform