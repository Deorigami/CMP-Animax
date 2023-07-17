package com.eyedea.shared_ui_components.util

import androidx.compose.runtime.Composable

internal typealias callback = () -> Unit
internal typealias genericCallback<T> = (T) -> Unit
internal typealias composableCallback = @Composable () -> Unit