package com.eyedea.shared_ui_components

actual fun getPlatform(): Platform {
    return object : Platform {
        override val name: String
            get() = "dekstop"
    }
}