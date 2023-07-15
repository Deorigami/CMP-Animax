package com.eyedea.shared_core.base

import kotlinx.serialization.Serializable

@Serializable
data class BaseRespondDto<T>(
    val status: Int,
    val data: T
) {
    companion object {
        fun <T, R> BaseRespondDto<R>.toBaseRespondEntity(data : (R) -> T) : BaseRespondEntity<T> {
            return BaseRespondEntity(data.invoke(this.data))
        }
    }
}