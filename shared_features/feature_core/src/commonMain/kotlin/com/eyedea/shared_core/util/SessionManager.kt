package com.eyedea.shared_core.util

import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set

object SessionManager {
    private val setting = Settings()

    var registeredNumber : String?
        get() = setting[Key.REGISTERED_NUMBER_KEY]
        set(value) {
            setting[Key.REGISTERED_NUMBER_KEY] = value
        }

    private object Key {
        const val REGISTERED_NUMBER_KEY = "REGISTERED_NUMBER_KEY"
    }
}