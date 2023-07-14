package com.eyedea.feature_dashboard.landing

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator

object DashboardLandingScreen : Screen {
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.current

    }
}