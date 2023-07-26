package di

import com.eyedea.feature_dashboard.featureDashboardModule
import com.eyedea.feature_detail.featureDetailModule
import com.eyedea.service_anime.serviceAnimeModule
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.core.context.startKoin

fun initKoin() {
    // start Koin
    startKoin {
        modules(
            listOf(
                serviceAnimeModule(),
                featureDashboardModule(),
                featureDetailModule()
            ).flatten().plus(routerModule()),
        )
    }
}

fun setupNappier(){
    Napier.base(DebugAntilog())
}