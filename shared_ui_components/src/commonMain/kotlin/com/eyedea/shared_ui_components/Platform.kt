package com.eyedea.shared_ui_components

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform