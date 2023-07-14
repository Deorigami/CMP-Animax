package com.eyedea.shared_core.base

sealed class BaseState<out R> {
    object Loading : BaseState<Nothing>()
    data class Success<R>(val data : R, val status: Int = 200) : BaseState<R>()
    data class Error(val status : Int, val message : String) : BaseState<Nothing>()
}