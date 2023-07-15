package com.eyedea.shared_core.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

actual abstract class BaseViewModel actual constructor() {
    actual val scope : CoroutineScope = CoroutineScope(Dispatchers.IO)
    protected actual fun onCleared() {
        clear()
    }

    private fun clear(){
        onCleared()
        scope.cancel()
        getStatefulData().forEach { it.cancel() }
    }

    actual abstract fun getStatefulData(): List<StatefulData<*, *>>

}