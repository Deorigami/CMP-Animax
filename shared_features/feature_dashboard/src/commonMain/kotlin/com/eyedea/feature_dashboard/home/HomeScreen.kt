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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.lifecycle.LifecycleEffect
import com.eyedea.feature_dashboard.DashboardRouter
import com.eyedea.feature_dashboard.home.mapper.AnimeThumbnailEntityMapper
import com.eyedea.service_anime.domain.entity.AnimeShowcaseEntity
import com.eyedea.shared_core.base.BaseTab
import com.eyedea.shared_ui_components.Res
import com.eyedea.shared_ui_components.composables.Button
import com.eyedea.shared_ui_components.composables.HomeRowList
import com.eyedea.shared_ui_components.style.bodySmallMedium
import com.eyedea.shared_ui_components.style.h4Bold
import com.seiko.imageloader.rememberImagePainter
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.compose.painterResource
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

data class HomeScreen(
    val screenModel: HomeScreenModel
) : BaseTab(), KoinComponent {

    override val router by inject<DashboardRouter>()

    @Composable
    override fun ComposeContent() {
        super.ComposeContent()
        val animeShowCaseListState = screenModel.popularAnime.state.collectAsState().value
        val newReleaseListState = screenModel.newRelease.state.collectAsState().value
        val trendingListState = remember(animeShowCaseListState.data) {
            animeShowCaseListState.data?.trending?.toMutableStateList()
        }

        val topListData = remember(animeShowCaseListState.data) {
            AnimeThumbnailEntityMapper().invoke(animeShowCaseListState.data?.top)
        }

        val popularListData = remember(animeShowCaseListState.data) {
            AnimeThumbnailEntityMapper().invoke(animeShowCaseListState.data?.popular)
        }

        val trendingListData = remember(trendingListState) {
            AnimeThumbnailEntityMapper().invoke(trendingListState)
        }
        val newReleaseList = remember(newReleaseListState.data) {
            AnimeThumbnailEntityMapper().invoke(newReleaseListState.data?.take(10))
        }

        val containerScrollState = rememberScrollState()


        Column (modifier = Modifier.verticalScroll(containerScrollState)){
            trendingListState?.sortedByDescending { it.rating.toDoubleOrNull() }?.first()?.let {
                Header(
                    modifier = Modifier.fillMaxWidth().background(Color.Black),
                    data = it
                )
            }
            HomeRowList(
                headerTitle = "Trending",
                list = trendingListData,
                modifier = Modifier.padding(top = 24.dp),
                onCardPressed = {
                    router.navigateToAnimeDetail(trendingListState?.getOrNull(it)?.animeId ?: return@HomeRowList)
                },
            )
            HomeRowList(
                headerTitle = "Top",
                list = topListData,
                modifier = Modifier.padding(top = 24.dp),
                onCardPressed = {
                    router.navigateToAnimeDetail(animeShowCaseListState.data?.top?.getOrNull(it)?.animeId ?: return@HomeRowList)
                },
            )
            HomeRowList(
                headerTitle = "Popular",
                list = popularListData,
                modifier = Modifier.padding(top = 24.dp),
                onCardPressed = {
                    router.navigateToAnimeDetail(animeShowCaseListState.data?.popular?.getOrNull(it)?.animeId ?: return@HomeRowList)
                },
            )
            HomeRowList(
                headerTitle = "New Episode Releases",
                list = newReleaseList,
                modifier = Modifier.padding(top = 24.dp, bottom = 127.dp),
                onRightHeaderPressed = {  },
                onCardPressed = {
                    router.navigateToAnimeDetail(newReleaseListState.data?.getOrNull(it)?.animeId ?: return@HomeRowList)
                }
            )
        }
    }

    override val activeIcon: ImageResource get() = Res.images.ic_home_selected
    override val inActiveIcon: ImageResource get() = Res.images.ic_home
    override val label: String get() = "Home"


    @Composable
    fun Header(
        modifier: Modifier = Modifier,
        data : AnimeShowcaseEntity
    ) {
        Box(modifier.fillMaxWidth()) {
            val image = rememberImagePainter(data.animeImg)

            Image(
                painter = image,
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
                    data.animeTitle,
                    style = h4Bold(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    data.genres,
                    style = bodySmallMedium(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Row(modifier = Modifier.padding(top = 8.dp)) {
                    Button.SolidMedium(leadingIcon = painterResource(Res.images.ic_play), text = "Play")
                    Button.OutlinedMedium("My List", leadingIcon = painterResource(Res.images.ic_add), modifier = Modifier.padding(start = 8.dp))
                }
            }
        }
    }

}