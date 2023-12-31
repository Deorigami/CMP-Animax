package com.eyedea.shared_core.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun <T> Flow<T>.asCommonFlow() : CommonFlow<T> = CommonFlow(this)

class CommonFlow<T>(private val origin: Flow<T>) : MutableStateFlow<T> by origin as MutableStateFlow<T> {
    fun watch(block: (T) -> Unit): Closeable {
        val job = Job()
        onEach { block(it) }.launchIn(CoroutineScope(job + Dispatchers.Main))

        return object : Closeable {
            override fun close() {
                job.cancel()
            }
        }
    }
}

interface Closeable {
    fun close()
}