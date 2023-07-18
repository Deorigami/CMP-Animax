import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import cafe.adriel.voyager.core.stack.StackEvent
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.ScreenTransition
import cafe.adriel.voyager.transitions.ScreenTransitionContent
import cafe.adriel.voyager.transitions.SlideOrientation
import cafe.adriel.voyager.transitions.SlideTransition
import com.eyedea.feature_dashboard.featureDashboardModule
import com.eyedea.feature_dashboard.landing.DashboardLandingScreen
import com.eyedea.service_anime.serviceAnimeModule
import com.eyedea.shared_ui_components.Res
import com.eyedea.shared_ui_components.util.generateImageLoader
import com.seiko.imageloader.LocalImageLoader
import dev.icerock.moko.resources.compose.painterResource
import di.routerModule
import io.github.aakira.napier.Napier
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.koin.core.context.startKoin

@OptIn(ExperimentalAnimationApi::class, ExperimentalResourceApi::class)
@Composable
fun App() {
    startKoin {
        modules(
            listOf(
                serviceAnimeModule(),
                featureDashboardModule(),
            ).flatten().plus(routerModule()),
        )
    }
    CompositionLocalProvider(
        LocalImageLoader provides generateImageLoader()
    ){
        MaterialTheme {
            Navigator(
                DashboardLandingScreen,
                onBackPressed = {
                    Napier.d(it.toString(), tag = "BACKPRESS")
                    true
                },
            ){
                SlideTransition(it, modifier = Modifier.background(color = Color.Black))
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
private fun CustomSlideTransition(
    navigator: Navigator,
    modifier: Modifier = Modifier,
    orientation: SlideOrientation = SlideOrientation.Horizontal,
    animationSpec: FiniteAnimationSpec<IntOffset> = spring(
        stiffness = Spring.StiffnessMediumLow,
        visibilityThreshold = IntOffset.VisibilityThreshold
    ),
    content: ScreenTransitionContent = { it.Content() }
) {
    ScreenTransition(
        navigator = navigator,
        modifier = modifier,
        content = content,
        transition = {
            val (initialOffset, targetOffset) = when (navigator.lastEvent) {
                StackEvent.Pop -> ({ size: Int -> -size}) to ({ size: Int -> size / 2 })
                else -> ({ size: Int -> size / 2 }) to ({ size: Int -> -size })
            }

            when (orientation) {
                SlideOrientation.Horizontal ->
                    slideInHorizontally(animationSpec, initialOffset) with
                            slideOutHorizontally(animationSpec, targetOffset)
                SlideOrientation.Vertical ->
                    slideInVertically(animationSpec, initialOffset) with
                            slideOutVertically(animationSpec, targetOffset)
            }
        }
    )
}

expect fun getPlatformName(): String