package com.eyedea.shared_core

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform