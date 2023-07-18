package com.eyedea.shared_ui_components.style

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.eyedea.shared_ui_components.Res
import dev.icerock.moko.resources.compose.fontFamilyResource


val urbanistBold : FontFamily
    @Composable get() = fontFamilyResource(Res.fonts.urbanist_bold.urbanist_bold)

val urbanistSemiBold : FontFamily
    @Composable get() = fontFamilyResource(Res.fonts.urbanist_semibold.urbanist_semibold)

val urbanistMedium : FontFamily
    @Composable get() = fontFamilyResource(Res.fonts.urbanist_medium.urbanist_medium)

// --------------------------------------------------------------------------------
// URBANIST BOLD
// --------------------------------------------------------------------------------

@Composable fun bodySmallBold() = TextStyle(
    fontSize = 10.sp,
    fontFamily = urbanistBold,
    color = Color.White
)

@Composable fun bodyLargeBold() = TextStyle(
    fontSize = 16.sp,
    fontFamily = urbanistBold,
    color = Color.White
)

@Composable fun h6Bold() = TextStyle(
    fontSize = 18.sp,
    fontFamily = urbanistBold,
    color = Color.White
)

@Composable fun h5Bold() = TextStyle(
    fontSize = 20.sp,
    fontFamily = urbanistBold,
    color = Color.White
)

@Composable fun h4Bold() = TextStyle(
    fontSize = 24.sp,
    fontFamily = urbanistBold,
    color = Color.White
)

@Composable fun h2Bold() = TextStyle(
    fontSize = 40.sp,
    fontFamily = urbanistBold,
    color = Color.White
)

// --------------------------------------------------------------------------------
// URBANIST MEDIUM
// --------------------------------------------------------------------------------

@Composable fun bodyXSmallMedium() = TextStyle(
    fontSize = 10.sp,
    fontFamily = urbanistMedium,
    color = Color.White
)
@Composable fun bodySmallMedium() = TextStyle(
    fontSize = 12.sp,
//    fontFamily = urbanistMedium,
    color = Color.White
)

// --------------------------------------------------------------------------------
// URBANIST SEMI BOLD
// --------------------------------------------------------------------------------

@Composable fun bodyXSmallSemiBold() = TextStyle(
    fontSize = 10.sp,
    fontFamily = urbanistSemiBold,
    color = Color.White
)

@Composable fun bodySmallSemiBold() = TextStyle(
    fontSize = 12.sp,
    fontFamily = urbanistSemiBold,
    color = Color.White
)

@Composable fun bodyMediumSemibold() = TextStyle(
    fontSize = 14.sp,
    fontFamily = urbanistSemiBold,
    color = Color.White
)

@Composable fun urbanistSemiBold16() = TextStyle(
    fontSize = 16.sp,
    fontFamily = urbanistSemiBold,
    color = Color.White
)

@Composable fun urbanistSemiBold18() = TextStyle(
    fontSize = 18.sp,
    fontFamily = urbanistSemiBold,
    color = Color.White
)
