package com.eyedea.feature_dashboard

actual fun getPlatform(): Platform {
    return object : Platform {
        override val name: String
            get() = "dekstop"
    }
}