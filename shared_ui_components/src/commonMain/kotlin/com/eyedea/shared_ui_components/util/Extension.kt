package com.eyedea.shared_ui_components.util

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.scale
import kotlinx.coroutines.launch

fun Modifier.scaledClickable(onClick: callback): Modifier = composed {
    val coroutineScope = rememberCoroutineScope()
    val scaleValue = remember {
        Animatable(1f)
    }
    clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() },
    ) {
        coroutineScope.launch {
            scaleValue.animateTo(
                0.9f,
                animationSpec = tween(50),
            )
            scaleValue.animateTo(
                1f,
                animationSpec = tween(50),
            )
            onClick.invoke()
        }
    }.then(Modifier.scale(scaleValue.value))
}