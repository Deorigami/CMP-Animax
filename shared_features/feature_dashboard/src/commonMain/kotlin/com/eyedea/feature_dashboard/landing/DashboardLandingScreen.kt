package com.eyedea.feature_dashboard.landing

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.lifecycle.LifecycleEffect
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.eyedea.feature_dashboard.download.DownloadScreen
import com.eyedea.feature_dashboard.home.HomeScreen
import com.eyedea.feature_dashboard.home.HomeScreenModel
import com.eyedea.feature_dashboard.my_list.MyListScreen
import com.eyedea.feature_dashboard.profile.ProfileScreen
import com.eyedea.feature_dashboard.releases.ReleaseDateScreen
import com.eyedea.shared_core.base.BaseTab
import com.eyedea.shared_core.util.callback
import com.eyedea.shared_core.util.genericCallback
import com.eyedea.shared_ui_components.style.Colors
import com.eyedea.shared_ui_components.style.bodySmallBold
import dev.icerock.moko.resources.compose.painterResource
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

object DashboardLandingScreen : Screen {

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun Content() {
        val localNavigator = LocalNavigator.currentOrThrow
        val homeScreenViewModel = koinInject<HomeScreenModel>()
        val homeScreen = HomeScreen(homeScreenViewModel)

        LifecycleEffect(
            onStarted = {
                homeScreenViewModel.initData()
            }
        )

        val screenList = listOf(homeScreen, ReleaseDateScreen, MyListScreen, DownloadScreen, ProfileScreen)
        val pagerState = rememberPagerState()
        val coroutineScope = rememberCoroutineScope()
        Box(modifier = Modifier.fillMaxSize()) {
            HorizontalPager(
                pageCount = screenList.size,
                state = pagerState,
                userScrollEnabled = false
            ){
                screenList[it].apply {
                    navigator = localNavigator
                }.ComposeContent()
            }
            TabNavigator(
                modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth().background(Color.White),
                tabs = screenList,
                activeTab = pagerState.currentPage,
                onTabPressed = {
                    coroutineScope.launch {
                        pagerState.scrollToPage(it)
                    }
                }
            )
        }
    }

    @Composable
    fun TabNavigator(
        modifier: Modifier = Modifier,
        tabs : List<BaseTab>,
        onTabPressed: genericCallback<Int> = {},
        activeTab : Int = 0
    ) {
        LazyRow(modifier = modifier) {
            itemsIndexed(tabs){index , data ->
                TabItem(
                    data,
                    modifier = Modifier.fillParentMaxWidth(1f / tabs.size),
                    onTabPressed = {onTabPressed.invoke(index)},
                    isActive = activeTab == index
                )
//                Image(painterResource(data.activeIcon), "")
            }
        }
    }

    @Composable
    private fun TabItem(tab: BaseTab, modifier: Modifier = Modifier, onTabPressed: callback = {}, isActive : Boolean = false){
        Column(
            modifier = modifier.fillMaxWidth()
                .clickable { onTabPressed.invoke() }
                .background(Color.Black)
                .padding(vertical = 8.dp, horizontal = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val textColor = if (isActive) Colors.primary500() else Colors.greyScale500()
            val icon = if (isActive) tab.activeIcon else tab.inActiveIcon
            Image(
                painter = painterResource(icon),
                "",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = tab.label,
                style = bodySmallBold().copy(textColor),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}