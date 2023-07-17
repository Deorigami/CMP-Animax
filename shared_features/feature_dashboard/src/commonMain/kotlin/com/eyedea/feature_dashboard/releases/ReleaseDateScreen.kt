package com.eyedea.feature_dashboard.releases

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.eyedea.shared_core.base.BaseTab
import com.eyedea.shared_ui_components.Res
import dev.icerock.moko.resources.ImageResource

object ReleaseDateScreen : BaseTab() {
    @Composable
    override fun ComposeContent() {
        Box(
            modifier = Modifier.fillMaxSize().background(Color.DarkGray)
        ){}
    }

    override val activeIcon: ImageResource get() = Res.images.ic_calendar_selected
    override val inActiveIcon: ImageResource get() = Res.images.ic_calendar
    override val label: String get() = "Release Calendar"
}