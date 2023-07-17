package di

import com.eyedea.feature_dashboard.featureDashboardModule
import com.eyedea.service_anime.serviceAnimeModule
import org.koin.core.context.startKoin

fun initKoin() {
    // start Koin
    startKoin {
        modules(
            listOf(
                serviceAnimeModule(),
                featureDashboardModule(),
            ).flatten().plus(routerModule()),
        )
    }
}