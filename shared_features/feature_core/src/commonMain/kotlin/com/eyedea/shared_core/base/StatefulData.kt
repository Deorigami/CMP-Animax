package com.eyedea.shared_core.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class StatefulData<P, R>(
    private val useCase: BaseUseCase<P, R>,
    private val scope: CoroutineScope
) {

    private lateinit var generatedParam : () -> P
    constructor(
        useCase: BaseUseCase<P, R>,
        scope: CoroutineScope,
        param : () -> P
    ) : this(useCase, scope){
        this.generatedParam = param
    }

    private val _state = MutableStateFlow<StateData<R>>(
        StateData(
            data = null,
            isLoading = false,
            message = null,
            status = null,
        )
    )
    private val isLoading = MutableStateFlow(false)
    val state : CommonFlow<StateData<R>> get() = _state.asCommonFlow()
    fun loadData(param: P){
        if (useCase.isActive) return
        isLoading.value = true
        _state.value = StateData(
            data = null,
            isLoading = true,
            message = null,
            status = null
        )
        scope.launch {
            useCase.execute(
                param,
                scope,
            ){
                when(it){
                    is BaseState.Error -> {
                        _state.value = StateData(
                            data = null,
                            isLoading = false,
                            message = it.message,
                            status = it.status
                        )
                    }
                    is BaseState.Success -> {
                        _state.value = StateData(
                            data = it.data,
                            isLoading = false,
                            message = "Success",
                            status = it.status
                        )
                    }
                    else -> {}
                }
            }
        }
    }

    fun fetch(onSuccess: (StateData<R>) -> Unit = {}){
        if (useCase.isActive) return
        isLoading.value = true
        _state.value = StateData(
            data = null,
            isLoading = true,
            message = null,
            status = null
        )
        scope.launch {
//            delay(5000)
            useCase.execute(
                generatedParam.invoke(),
                scope,
            ){
                when(it){
                    is BaseState.Error -> {
                        _state.value = StateData(
                            data = null,
                            isLoading = false,
                            message = it.message,
                            status = it.status
                        )
                        onSuccess.invoke(_state.value)
                    }
                    is BaseState.Success -> {
                        _state.value = StateData(
                            data = it.data,
                            isLoading = false,
                            message = "Success",
                            status = it.status
                        )
                        onSuccess.invoke(_state.value)
                    }
                    else -> {}
                }
            }
        }
    }

    fun cancel(){
        useCase.cancel()
    }
}

data class StateData<R>(
    val data : R?,
    val isLoading : Boolean = false,
    val message : String? = null,
    val status: Int? = null
)

data class Error(val status : Int, val message : String)