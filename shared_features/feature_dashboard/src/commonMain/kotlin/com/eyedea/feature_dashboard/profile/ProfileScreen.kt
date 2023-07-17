package com.eyedea.feature_dashboard.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.eyedea.shared_core.base.BaseTab
import com.eyedea.shared_ui_components.Res
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.compose.painterResource

object ProfileScreen : BaseTab() {
    @Composable
    override fun ComposeContent() {
        Box(
            modifier = Modifier.fillMaxSize().background(Color.Black)
        ){}
    }

    override val activeIcon: ImageResource get() = Res.images.ic_profile_selected
    override val inActiveIcon: ImageResource get() = Res.images.ic_profile
    override val label: String get() = "Profile"
}