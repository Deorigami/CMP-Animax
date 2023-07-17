package com.eyedea.feature_detail.anime_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.eyedea.shared_ui_components.Res
import com.eyedea.shared_ui_components.composables.Button
import com.eyedea.shared_ui_components.style.Colors
import com.eyedea.shared_ui_components.style.bodyMediumSemibold
import com.eyedea.shared_ui_components.style.bodySmallMedium
import com.eyedea.shared_ui_components.style.bodySmallSemiBold
import com.eyedea.shared_ui_components.style.bodyXSmallSemiBold
import com.eyedea.shared_ui_components.style.h4Bold
import com.eyedea.shared_ui_components.util.scaledClickable
import dev.icerock.moko.resources.compose.painterResource

data class AnimeDetailScreen(
    val id : Int
) : Screen {
    @Composable
    override fun Content() {
        Column (modifier = Modifier.fillMaxSize()){
            Header()
            TitleSection(title = "Demon Slayer (Kimetsu no Yaiba)",modifier = Modifier.padding(horizontal = 24.dp).padding(top = 24.dp))
            DetailInfo(modifier = Modifier.fillMaxWidth().padding(top = 16.dp).padding(horizontal = 24.dp))
            ActionButtons(modifier = Modifier.fillMaxWidth().padding(top = 16.dp))
            Text("Genre: Action, Martial Arts, Adventure, Dark Fantasy, Thriller, Martial Arts, Adventure, Dark Fantasy, Thriller", )

        }
    }

    @Composable
    private fun ActionButtons(modifier: Modifier) {
        Row(modifier.fillMaxWidth()) {
            Button.SolidMedium(
                modifier = Modifier.weight(1f).padding(start = 24.dp, end = 4.dp),
                text = "Play",
                leadingIcon = painterResource(Res.images.ic_play)
            )
            Button.OutlinedMedium(
                modifier = Modifier.weight(1f).padding(start = 4.dp, end = 24.dp),
                borderColor = Colors.primary500(),
                text = "Download",
                leadingIcon = painterResource(Res.images.ic_download_selected)
            )
        }
    }

    @Composable
    private fun DetailInfo(modifier: Modifier) {
        Row(modifier, verticalAlignment = Alignment.CenterVertically) {
            Image(painterResource(Res.images.ic_star), "", modifier = Modifier.size(20.dp).padding(end = 8.dp))
            Text("9.8", modifier = Modifier.padding(end = 8.dp), style = bodySmallSemiBold().copy(color = Colors.primary500()))
            Image(painterResource(Res.images.ic_simple_arrow_right), "", modifier = Modifier.padding(end = 8.dp))
            Text("2022", style = bodyMediumSemibold(), modifier = Modifier.padding(end = 8.dp))
        }
    }

    @Composable
    private fun TitleSection(title : String = "", modifier: Modifier = Modifier) {
        Row(modifier = modifier.fillMaxWidth().height(IntrinsicSize.Min), verticalAlignment = Alignment.CenterVertically){
            Text(title, modifier = Modifier.weight(1f), style = h4Bold(), maxLines = 1, overflow = TextOverflow.Ellipsis)
            Image(painterResource(Res.images.ic_bookmark), "", modifier = Modifier.padding(end = 8.dp).size(20.dp))
            Image(painterResource(Res.images.ic_send), "", modifier = Modifier.size(20.dp))
        }
    }

    @Composable
    private fun Header(
        modifier: Modifier = Modifier,
    ) {
        val navigator = LocalNavigator.currentOrThrow
        Box(modifier.fillMaxWidth().height(IntrinsicSize.Min)) {
            Image(
                painter = painterResource(Res.images.hero_dashboard_bg),
                "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth().height(320.dp),
            )
            Box(modifier = Modifier.fillMaxWidth().padding(24.dp).padding(top = 24.dp).align(Alignment.TopCenter)) {
                Image(
                    painter = painterResource(Res.images.ic_arrow_left),
                    contentDescription = "",
                    modifier = Modifier.size(28.dp).scaledClickable {
                        navigator.pop()
                    }
                )
                Image(
                    painter = painterResource(Res.images.ic_tv_cast),
                    contentDescription = "",
                    modifier = Modifier.size(28.dp).align(Alignment.CenterEnd)
                )
            }
        }
    }
}
