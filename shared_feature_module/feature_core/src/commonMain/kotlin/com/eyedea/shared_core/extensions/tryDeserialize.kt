package com.eyedea.shared_core.extensions

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

@OptIn(ExperimentalSerializationApi::class)
inline fun <reified P> tryDeserialize(
    json: String,
    onFailedDeserialize: (msg: String) -> Unit = {}
) : P {
    val formatter = Json {
        isLenient = true
        ignoreUnknownKeys = true
        explicitNulls = false
    }
    return try {
        formatter.decodeFromString(json)
    }catch (e: Exception){
        onFailedDeserialize.invoke(e.message.toString())
        throw Throwable(e.message)
    }
}