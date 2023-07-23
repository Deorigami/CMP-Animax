package com.eyedea.shared_core.base

import com.eyedea.shared_core.util.callback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class StatefulFlow<P, R>(
    private val useCase: FlowUseCase<P, R>,
    private val scope: CoroutineScope
) {

    private lateinit var generatedParam : () -> P

    private var onComplete: callback = {}

    constructor(
        useCase: FlowUseCase<P, R>,
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

    fun setOnComplete(
        onComplete: callback = {}
    ){
        this.onComplete = onComplete
    }

    fun loadData(param: P = generatedParam.invoke()){
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
                onComplete.invoke()
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
                onComplete.invoke()
            }
        }
    }

    fun cancel(){
        useCase.cancel()
    }
}