package com.eyedea.shared_core.base

import com.eyedea.shared_core.extensions.tryDeserialize
import io.github.aakira.napier.Napier
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.statement.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.intOrNull
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

abstract class BaseUseCase<P, R> {

    abstract val default: R
    abstract suspend fun build(param: P): BaseRespondEntity<R>
    private var job: Job? = null
    val isActive get() = job?.isActive ?: false

    fun execute(
        param: P,
        scope: CoroutineScope,
        onResult: (BaseState<R>) -> Unit,
    ) {
        job = scope.launch {
            onResult(either {
                build(param)
            })
        }
    }

    fun cancel() = job?.cancel()

    private suspend fun either(block: suspend () -> BaseRespondEntity<R>): BaseState<R> = kotlin.runCatching {
        val response = block.invoke()
        BaseState.Success(response.data ?: default, response.status)
    }.getOrElse {
        when (it) {
            is ClientRequestException -> BaseState.Error(it.response.status.value, it.message)
            is UseCaseException -> BaseState.Success(default, it.statusCode)
            else -> BaseState.Error(-1, it.message ?: "")
        }
    }


}

suspend inline fun <reified DTO, ENTITY> HttpResponse.dtoToBaseRespondEntity(block: (DTO) -> ENTITY): BaseRespondEntity<ENTITY> {
    val dto = tryDeserialize<DTO>(this.bodyAsText()) {
        return BaseRespondEntity(error = Throwable(it))
    } ?: return BaseRespondEntity(error = Throwable("SOMETHING WENT WRONG"))
    return BaseRespondEntity(block.invoke(dto))
}

suspend inline fun <reified DTO, ENTITY> HttpResponse.toBaseRespondEntity(block: (DTO) -> ENTITY): BaseRespondEntity<ENTITY> {
    if (this.status.value >= 300) throw ClientRequestException(this, "")
    return kotlin.runCatching {
        val dto = tryDeserialize<BaseRespondDto<DTO>>(this.bodyAsText().also {
            Napier.d(tag = "ANGGATAG") { "Response From (${this.call.request.url}) : "}
            Napier.d(tag = "ANGGATAG") { it }
        })
        return BaseRespondEntity(dto.data?.let {
            val result = block.invoke(it)
            result
        }, error = null, status = status.value)
    }.getOrElse {
        val json = Json.parseToJsonElement(bodyAsText()).jsonObject
        val data = json.getOrElse("data") { null }
        val status = json.getOrElse("status") { null }?.jsonPrimitive?.intOrNull ?: this.status.value

        if (data == null && (status < 300)) BaseRespondEntity(status = status, error = it)
        else throw UseCaseException(statusCode = status, message = it.message)
    }
}

data class UseCaseException(
    val statusCode: Int,
    override val message: String? = ""
) : Exception()