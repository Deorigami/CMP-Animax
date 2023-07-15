package com.eyedea.service_anime

actual fun getPlatform(): Platform {
    return object : Platform {
        override val name: String
            get() = "Desktop"
    }
}