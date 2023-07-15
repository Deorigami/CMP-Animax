package com.eyedea.shared_core

actual fun getPlatform(): Platform = object : Platform {
    override val name: String
        get() = "desktop"
}