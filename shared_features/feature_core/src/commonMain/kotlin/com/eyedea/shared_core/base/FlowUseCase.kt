package com.eyedea.shared_core.base

import io.github.aakira.napier.Napier
import io.ktor.client.plugins.ClientRequestException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

abstract class FlowUseCase <P, R> {

    abstract val default : R
    abstract suspend fun build(param: P) : Flow<BaseRespondEntity<R>>
    private var job : Job? = null
    val isActive get() = job?.isActive ?: false

    fun execute(
        param: P,
        scope: CoroutineScope,
        onResult: (BaseState<R>) -> Unit
    ){
        job = scope.launch {
            kotlin.runCatching {
                build(param).onEach {
                    onResult(either {
                        it
                    })
                }.collect()
            }.getOrElse {
                Napier.e(tag = "EXECUTE_FAILED") { it.message ?: "Something Went Wrong" }
                onResult(BaseState.Error(500, it.message ?: "Something Went Wrong"))
            }
        }
    }

    fun cancel() = job?.cancel()

    private suspend fun either(block : suspend () -> BaseRespondEntity<R>) : BaseState<R> = kotlin.runCatching {
        val response = block.invoke()
        BaseState.Success(response.data ?: default, response.status)
    }.getOrElse {
        when(it){
            is ClientRequestException -> BaseState.Error(it.response.status.value, it.message)
            is UseCaseException -> BaseState.Success(default, it.statusCode)
            else -> BaseState.Error(-1, it.message ?: "")
        }
    }



}