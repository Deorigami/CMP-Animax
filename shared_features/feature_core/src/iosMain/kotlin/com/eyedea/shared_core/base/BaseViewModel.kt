package com.eyedea.shared_core.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

actual abstract class BaseViewModel {
    actual val scope : CoroutineScope = MainScope()
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