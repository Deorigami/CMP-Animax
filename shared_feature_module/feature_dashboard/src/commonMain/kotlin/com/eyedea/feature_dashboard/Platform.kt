package com.eyedea.feature_dashboard

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform