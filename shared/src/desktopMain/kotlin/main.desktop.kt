import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import com.eyedea.feature_dashboard.featureDashboardModule
import com.eyedea.service_anime.serviceAnimeModule
import org.koin.core.context.startKoin

actual fun getPlatformName(): String = "Desktop"

@Composable fun MainView() = App()

@Preview
@Composable
fun AppPreview() {
    startKoin {
        modules(listOf(
            serviceAnimeModule(),
            featureDashboardModule()
        ).flatten())
    }
    App()
}