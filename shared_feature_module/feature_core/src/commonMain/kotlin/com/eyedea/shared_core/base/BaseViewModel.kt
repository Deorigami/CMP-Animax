package com.eyedea.shared_core.base

import kotlinx.coroutines.CoroutineScope

expect abstract class BaseViewModel() {
    val scope : CoroutineScope
    protected fun onCleared()

    abstract fun getStatefulData() : List<StatefulData<*, *>>
}