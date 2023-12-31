package com.eyedea.shared_core.base

import kotlinx.serialization.ContextualSerializer
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.JsonUnquotedLiteral
import kotlinx.serialization.serializer


@Serializable
data class BaseRespondEntity<R> constructor(
    val data : R? = null,
    @Serializable(with = ThrowableSerializer::class) val error : Throwable? = null,
    val status : Int = 200
)

internal object DynamicLookupSerializer : KSerializer<Any> {
    @OptIn(ExperimentalSerializationApi::class)
    override val descriptor: SerialDescriptor = ContextualSerializer(Any::class, null, emptyArray()).descriptor

    @Suppress("UNCHECKED_CAST")
    @OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
    override fun serialize(encoder: Encoder, value: Any) {
        val actualSerializer = encoder.serializersModule.getContextual(value::class)
            ?: value::class.serializer()
        if (value is String) {
            val jsonUnquotedLiteral = JsonUnquotedLiteral(value.takeIf { it != "null" && it.isNotBlank() })
            encoder.encodeSerializableValue(JsonPrimitive.serializer(), jsonUnquotedLiteral)
        } else {
            encoder.encodeSerializableValue(actualSerializer as KSerializer<Any>, value)
        }
    }

    override fun deserialize(decoder: Decoder): Any {
        error("Unsupported")
    }
}
object ThrowableSerializer : KSerializer<Throwable> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("Throwable")

    override fun serialize(encoder: Encoder, value: Throwable) {
        encoder.encodeString(value.message ?: "")
    }

    override fun deserialize(decoder: Decoder): Throwable {
        val message = decoder.decodeString()
        return Throwable(message)
    }
}