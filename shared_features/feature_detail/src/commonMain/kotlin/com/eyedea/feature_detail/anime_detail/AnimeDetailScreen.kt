package com.eyedea.feature_detail.anime_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.lifecycle.LifecycleEffect
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.eyedea.shared_ui_components.Res
import com.eyedea.shared_ui_components.composables.Button
import com.eyedea.shared_ui_components.composables.EpisodeThumbnailData
import com.eyedea.shared_ui_components.composables.EpisodeThumbnailList
import com.eyedea.shared_ui_components.composables.ExpandableText
import com.eyedea.shared_ui_components.style.Colors
import com.eyedea.shared_ui_components.style.bodyMediumSemiBold
import com.eyedea.shared_ui_components.style.bodySmallMedium
import com.eyedea.shared_ui_components.style.bodySmallSemiBold
import com.eyedea.shared_ui_components.style.h4Bold
import com.eyedea.shared_ui_components.util.scaledClickable
import com.seiko.imageloader.LocalImageLoader
import com.seiko.imageloader.rememberImagePainter
import dev.icerock.moko.resources.compose.painterResource
import io.github.aakira.napier.Napier
import org.koin.compose.koinInject

data class AnimeDetailScreen(
    val id : String
) : Screen {
    @Composable
    override fun Content() {

        val viewModel = koinInject<AnimeDetailViewModel>()
        val animeDetailState = viewModel.animeDetail.state.collectAsState().value.data
        val contentDetailScrollState = rememberScrollState()
        val episodeList = remember(animeDetailState) { mutableStateOf(animeDetailState?.episodes ?: emptyList()) }

        LifecycleEffect(
            onStarted = { viewModel.animeDetail.loadData(id) }
        )

        Napier.d(tag = "ANGGATAG") { animeDetailState.toString() }

        Column (modifier = Modifier.fillMaxSize()){
            Header(animeDetailState?.image ?: "")
            Column(modifier = Modifier.fillMaxWidth().weight(1f).verticalScroll(contentDetailScrollState)){
                TitleSection(title = animeDetailState?.title ?: "",modifier = Modifier.padding(horizontal = 24.dp).padding(top = 24.dp))
                DetailInfo(
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp).padding(horizontal = 24.dp),
                    rating = animeDetailState?.rating?.toDoubleOrNull() ?: 0.0,
                    yearAired = animeDetailState?.yearProduced ?: ""
                )
                ActionButtons(modifier = Modifier.fillMaxWidth().padding(top = 16.dp))
                Text(
                    modifier = Modifier.padding(horizontal = 24.dp).padding(top = 8.dp),
                    text = "Genre: ${animeDetailState?.genre}",
                    style = bodySmallMedium(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                ExpandableText(
                    text = animeDetailState?.synopsis ?: "",
                    style = bodySmallMedium(),
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp).padding(top = 8.dp)
                )
                EpisodeThumbnailList(
                    modifier = Modifier.fillMaxWidth().padding(top = 24.dp),
                    list = episodeList.value.mapIndexed { index, it ->
                        EpisodeThumbnailData(
                            imageUrl = it.image.ifEmpty { animeDetailState?.image ?: "" }.takeIf { !it.contains("animepahe") } ?: animeDetailState?.image ?: "",
                            title = it.title
                        )
                    }
                )
            }

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
    private fun DetailInfo(modifier: Modifier, rating : Double, yearAired: String) {
        Row(modifier, verticalAlignment = Alignment.CenterVertically) {
            Image(painterResource(Res.images.ic_star), "", modifier = Modifier.size(20.dp).padding(end = 8.dp), colorFilter = ColorFilter.tint(Colors.primary500()))
            Text(rating.toString(), modifier = Modifier.padding(end = 8.dp), style = bodySmallSemiBold().copy(color = Colors.primary500()))
            Image(painterResource(Res.images.ic_simple_arrow_right), "", modifier = Modifier.padding(end = 8.dp))
            Text(yearAired, style = bodyMediumSemiBold(), modifier = Modifier.padding(end = 8.dp))
        }
    }

    @Composable
    private fun TitleSection(title : String = "", modifier: Modifier = Modifier) {
        Row(modifier = modifier.fillMaxWidth().height(IntrinsicSize.Min), verticalAlignment = Alignment.CenterVertically){
            Text(title, modifier = Modifier.weight(1f).padding(end = 8.dp), style = h4Bold(), maxLines = 1, overflow = TextOverflow.Ellipsis)
            Image(painterResource(Res.images.ic_bookmark), "", modifier = Modifier.padding(end = 8.dp).size(20.dp))
            Image(painterResource(Res.images.ic_send), "", modifier = Modifier.size(20.dp))
        }
    }

    @Composable
    private fun Header(
        headerImage : String = "",
        modifier: Modifier = Modifier,
    ) {
        val navigator = LocalNavigator.currentOrThrow
        Box(modifier.fillMaxWidth().height(IntrinsicSize.Min)) {
            val image = if (headerImage.isNotEmpty()) rememberImagePainter(headerImage, imageLoader = LocalImageLoader.current, errorPainter = { painterResource(Res.images.hero_dashboard_bg) }) else ColorPainter(Colors.greyScale500())
            Image(
                painter = image,
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
