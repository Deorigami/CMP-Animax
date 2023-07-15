package com.eyedea.shared_core.extensions

import io.github.aakira.napier.Napier
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
        Napier.e(e.message ?: "", tag = "DESERIALIZE_FAILED")
        throw Throwable(e.message)
    }
}