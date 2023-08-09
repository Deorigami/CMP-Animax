package com.eyedea.shared_core.base

import com.eyedea.shared_core.extensions.tryDeserialize
import com.eyedea.shared_core.util.CacheUtil
import com.eyedea.shared_core.util.SessionManager
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.request
import io.ktor.http.HttpMethod
import io.ktor.http.HttpMethod.Companion.Get
import io.ktor.http.HttpMethod.Companion.Post
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

suspend inline fun <reified DTO, ENTITY> HttpClient.toFlow(
    url: String,
    method: HttpMethod = Get,
    getCache: Boolean = false,
    crossinline block: (DTO) -> ENTITY,
) : Flow<BaseRespondEntity<ENTITY>> = flow {

    if (getCache){
        kotlin.runCatching {
            val cache = CacheUtil.getCachedHttpResponse("${SessionManager.BASE_URL}$url")
            val cacheDto = cache?.let { tryDeserialize<DTO>(it) }
            cacheDto?.let { emit(BaseRespondEntity(it.let(block))) }
        }
    }

    val response = withContext(context = Dispatchers.Default) {
        when(method.value){
            Get.value -> get(url){ header("cached", getCache) }
            Post.value -> post(url)
            else -> null
        }
    }

    response?.dtoToBaseRespondEntity(isCached = getCache, block)?.let { emit(it) }
}

suspend inline fun <reified DTO, ENTITY> HttpResponse.dtoToBaseRespondEntity(isCached: Boolean = false, block: (DTO) -> ENTITY): BaseRespondEntity<ENTITY> {
    val dto = tryDeserialize<DTO>(bodyAsText().apply {
        Napier.d(tag = "HTTP_RESPONSE") { "${request.url} ${status.value}" }
        if (isCached) {
            CacheUtil.saveHttpResponse(call.request.url.toString(), this)
        }
    }){
        Napier.e(tag = "SERIALIZE_ERROR") { "$it : ${DTO::class.qualifiedName}" }
    }
    return BaseRespondEntity(block.invoke(dto))
}

data class UseCaseException(
    val statusCode: Int,
    override val message: String? = ""
) : Exception()