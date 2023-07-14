package com.eyedea.shared_core.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope

actual abstract class BaseViewModel : ViewModel() {
    actual val scope: CoroutineScope
        get() = viewModelScope

    actual override fun onCleared() {
        getStatefulData().forEach { it.cancel() }
        Log.d("BaseViewModel", "CLEARED");
    }

    actual abstract fun getStatefulData(): List<StatefulData<*, *>>
}