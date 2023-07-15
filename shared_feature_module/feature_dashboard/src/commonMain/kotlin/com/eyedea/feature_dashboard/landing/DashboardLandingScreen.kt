package com.eyedea.feature_dashboard.landing

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.eyedea.feature_dashboard.download.DownloadScreen
import com.eyedea.feature_dashboard.home.HomeScreen
import com.eyedea.feature_dashboard.my_list.MyListScreen
import com.eyedea.feature_dashboard.profile.ProfileScreen
import com.eyedea.feature_dashboard.releases.ReleaseDateScreen
import com.eyedea.shared_ui_components.style.Colors
import com.eyedea.shared_ui_components.style.bodySmallBold
import com.eyedea.shared_ui_components.style.bodySmallMedium
import com.eyedea.shared_ui_components.style.bodyXSmallMedium

object DashboardLandingScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        TabNavigator(
            HomeScreen
        ){
            Box(modifier = Modifier.fillMaxSize()) {
                CurrentTab()
                TabNavigator(
                    modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth().background(Color.White),
                    HomeScreen, ReleaseDateScreen, MyListScreen, DownloadScreen, ProfileScreen
                )
            }
        }
    }

    @Composable
    fun TabNavigator(
        modifier: Modifier = Modifier,
        vararg tabs: Tab
    ) {
        LazyRow(modifier = modifier) {
            items(tabs.asList()){
                TabItem(it, modifier = Modifier.fillParentMaxWidth(1f / tabs.size))
            }
        }
    }

    @Composable
    private fun TabItem(tab: Tab, modifier: Modifier = Modifier){
        val tabNavigator = LocalTabNavigator.current
        val isActive = tab == tabNavigator.current
        Column(
            modifier = modifier.fillMaxWidth()
                .clickable { tabNavigator.current = tab }
                .padding(vertical = 8.dp, horizontal = 4.dp),
        ) {
            tab.options.icon?.let { Image(
                painter = it,
                "",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) }
            Text(
                text = tab.options.title,
                style = (if (isActive) bodySmallBold() else bodyXSmallMedium()).copy(color = if (isActive) Colors.primary500() else Colors.greyScale500()),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}