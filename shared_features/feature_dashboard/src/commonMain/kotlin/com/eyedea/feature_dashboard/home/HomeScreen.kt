package com.eyedea.feature_dashboard.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.lifecycle.LifecycleEffect
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.eyedea.shared_ui_components.Res
import com.eyedea.shared_ui_components.composables.Button
import com.eyedea.shared_ui_components.composables.HomeRowList
import com.eyedea.shared_ui_components.composables.HomeRowListCardItem
import com.eyedea.shared_ui_components.composables.HomeRowListData
import com.eyedea.shared_ui_components.style.bodySmallMedium
import com.eyedea.shared_ui_components.style.h4Bold
import dev.icerock.moko.resources.compose.painterResource
import io.github.aakira.napier.Napier
import org.koin.compose.LocalKoinScope
import org.koin.compose.koinInject
import org.koin.compose.scope.rememberKoinScope
import org.koin.core.annotation.KoinExperimentalAPI

object HomeScreen : Tab {
    @Composable
    override fun Content() {
        val viewModel = koinInject<HomeScreenModel>()
        val popularAnimeState = viewModel.popularAnime.state.collectAsState().value
        val popularAnimeList = remember(popularAnimeState.data) {
            val rowListData : List<HomeRowListData> = popularAnimeState.data?.map { HomeRowListData(
                image = it.animeImg,
                rating = "9.8",
                index = ""
            ) } ?: emptyList()
            rowListData.toMutableStateList()
        }

        val containerScrollState = rememberScrollState()



        LifecycleEffect(
            onDisposed = {Napier.d("ON DISPOSED")},
            onStarted = {Napier.d { "ON STARTED" }}
        )

        Column (modifier = Modifier.verticalScroll(containerScrollState)){
            Header(modifier = Modifier.fillMaxWidth().background(Color.Black))
            HomeRowList(
                headerTitle = "Popular Anime",
                list = popularAnimeList,
                modifier = Modifier.padding(top = 24.dp)
            )
            HomeRowList(
                headerTitle = "New Episode Releases",
                list = popularAnimeList.map { it.copy(index = "") },
                modifier = Modifier.padding(top = 24.dp, bottom = 127.dp),
                onRightHeaderPressed = { Napier.d(tag = "ANGGATAG") { "Clicked" } }
            )
        }
    }

    @Composable
    fun Header(
        modifier: Modifier = Modifier,
    ) {
        Box(modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(Res.images.hero_dashboard_bg),
                "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth().height(400.dp).drawWithCache {
                    onDrawWithContent {
                        drawContent()
                        drawRect(Brush.verticalGradient(
                            0.5f to Color.Black.copy(alpha=0F),
                            1F to Color.Black.copy(alpha = 0.75f)
                        ))
                    }
                },
            )
            Column(modifier = Modifier.fillMaxSize().padding(24.dp).align(Alignment.TopCenter)) {
                Row(modifier = Modifier.padding(top = 48.dp), verticalAlignment = Alignment.CenterVertically) {
                    Image(painter = painterResource(Res.images.ic_animax_logo), "", modifier = Modifier.size(32.dp))
                    Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.End){
                        Image(painter = painterResource(Res.images.ic_search), "", modifier = Modifier.padding(end = 20.dp).size(28.dp))
                        Image(painter = painterResource(Res.images.ic_notification), "", modifier = Modifier.size(28.dp))
                    }
                }
            }
            Column(modifier = Modifier.fillMaxWidth(0.75f).align(Alignment.BottomStart).padding(start = 24.dp, bottom = 24.dp)) {
                Text(
                    "Demon Slayer: Kimetsu no Yaiba",
                    style = h4Bold(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    "Action, Shounen, Martial Arts, Adventure, Swordsman, Demon, Zero to Hero",
                    style = bodySmallMedium(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Row {
                    Button.SolidMedium(leadingIcon = painterResource(Res.images.ic_play), text = "Play")
                    Button.OutlinedMedium("My List", leadingIcon = painterResource(Res.images.ic_add), modifier = Modifier.padding(start = 8.dp))
                }
            }
        }
    }

    override val options: TabOptions
        @Composable
        get() {
            val navigator = LocalTabNavigator.current
            val isActive = navigator.current == this

            val title = "Home"
            val icon = painterResource(if (isActive) Res.images.ic_home_selected else Res.images.ic_home)

            return remember {
                TabOptions(
                    index = 3u,
                    title = title,
                    icon = icon
                )
            }
        }
}