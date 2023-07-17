package com.eyedea.shared_ui_components.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.eyedea.shared_ui_components.style.Colors
import com.eyedea.shared_ui_components.style.bodyLargeBold
import com.eyedea.shared_ui_components.style.bodyMediumSemibold
import com.eyedea.shared_ui_components.util.callback

object Button {
    @Composable
    fun SolidMedium(
        text: String,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        textStyle: TextStyle? = null,
        radius: Dp? = null,
    ) {
        Button(
            onClick = { onClick.invoke() },
            modifier = modifier,
            shape = RoundedCornerShape(radius ?: 10.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF9B58DF),
            ),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 0.dp,
            )
        ) {
            Text(text = text, style = textStyle ?: bodyMediumSemibold(), color = Color.White)
        }
    }

    @Composable
    fun SolidMedium(
        text: String = "",
        modifier: Modifier = Modifier,
        radius: Dp? = null,
        onClick: callback = {},
        leadingIcon: Painter? = null
    ) {
        Button(
            onClick = { onClick.invoke() },
            modifier = modifier,
            shape = when {
                radius != null -> RoundedCornerShape(radius)
                else -> RoundedCornerShape(100)
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Colors.primary500(),
            ),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 0.dp,
            )
        ) {
            leadingIcon?.let {
                Image(leadingIcon, "", modifier = Modifier.size(20.dp))
            }
            if (text.isNotEmpty()) {
                Text(text, Modifier.padding(start = 8.dp), style = bodyMediumSemibold())
            }
        }
    }

    @Composable
    fun OutlinedMedium(
        text: String = "",
        modifier: Modifier = Modifier,
        radius: Dp? = null,
        onClick: callback = {},
        leadingIcon: Painter? = null,
        borderColor: Color? = null
    ) {
        Button(
            onClick = { onClick.invoke() },
            modifier = modifier,
            shape = when {
                radius != null -> RoundedCornerShape(radius)
                else -> RoundedCornerShape(100)
            },
            border = BorderStroke(2.dp, borderColor ?: Color.White),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent,
            ),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 0.dp,
            )
        ) {
            leadingIcon?.let {
                Image(leadingIcon, "", modifier = Modifier.size(20.dp))
            }
            if (text.isNotEmpty()) {
                Text(text, Modifier.padding(start = 8.dp), style = bodyMediumSemibold())
            }
        }
    }

    @Composable
    fun Regular(modifier: Modifier = Modifier, onClick: () -> Unit, backgroundColor: Color? = null, content: @Composable RowScope.() -> Unit) {
        Button(
            onClick = { onClick.invoke() },
            modifier = modifier
                .height(65.dp),
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = backgroundColor ?: Color(0xFF9B58DF),
            ),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 0.dp,
            )
        ) {
            content()
        }
    }

    @Composable
    fun Regular(
        modifier: Modifier = Modifier,
        text: String,
        onClick: () -> Unit = {},
        isLoading: Boolean = false,
    ) {
        Button(
            onClick = { onClick.invoke() },
            modifier = modifier
                .height(60.dp),
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF9B58DF),
                contentColor = Color(0xFF9B58DF),
                disabledContentColor = Color.Black.copy(0.3f),
                disabledBackgroundColor = Color.Black.copy(0.3f),
            ),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 0.dp
            ),
            enabled = !isLoading
        ) {
            Text(
                text = text,
                style = bodyLargeBold(),
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}