package com.eyedea.feature_dashboard.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.eyedea.shared_ui_components.Res.fonts.urbanist_bold
import dev.icerock.moko.resources.FontResource
import dev.icerock.moko.resources.compose.fontFamilyResource

object HomeScreen : Tab {
    @Composable
    override fun Content() {

        Box(
            modifier = Modifier.fillMaxSize().background(Color.Cyan)
        ){
            Text(
                "Test Using Font",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 35.sp,
                    fontFamily = fontFamilyResource(urbanist_bold.urbanist_bold),
                ),
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }

    override val options: TabOptions
        @Composable
        get() {
            val title = "Download"
            val icon = rememberVectorPainter(Icons.Default.Home)

            return remember {
                TabOptions(
                    index = 3u,
                    title = title,
                    icon = icon
                )
            }
        }
}