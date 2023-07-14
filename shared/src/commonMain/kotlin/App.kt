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
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import cafe.adriel.voyager.core.stack.StackEvent
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.ScreenTransition
import cafe.adriel.voyager.transitions.ScreenTransitionContent
import cafe.adriel.voyager.transitions.SlideOrientation
import com.eyedea.feature_dashboard.landing.DashboardLandingScreen
import io.github.aakira.napier.Napier

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun App() {
    MaterialTheme {
        Navigator(
            DashboardLandingScreen,
            onBackPressed = {
                Napier.d(it.toString(), tag = "ANGGATAG")
                true
            },
        ){
            CustomSlideTransition(it)
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