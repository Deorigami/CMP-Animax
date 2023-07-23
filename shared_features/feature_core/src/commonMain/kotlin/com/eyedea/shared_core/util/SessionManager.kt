package com.eyedea.shared_core.util

import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set

object SessionManager {
    val setting = Settings()

    var registeredNumber : String?
        get() = setting[Key.REGISTERED_NUMBER_KEY]
        set(value) {
            setting[Key.REGISTERED_NUMBER_KEY] = value
        }

    inline fun <reified T> save(value: T, key: String){
        setting[key] = value
    }

    inline fun <reified T> get(key : String) : T? {
        return setting[key]
    }

    const val API_KEY = "fae494e3b9e506ccd74586c128c58f50"

    private object Key {
        const val REGISTERED_NUMBER_KEY = "REGISTERED_NUMBER_KEY"
    }
}