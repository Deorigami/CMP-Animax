package di

import com.eyedea.feature_dashboard.DashboardRouter
import com.eyedea.feature_util.router.GlobalRouter
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.createdAtStart
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

fun routerModule() = module {
    singleOf(::FeatureDashboardRouterImpl){
        bind<DashboardRouter>()
        createdAtStart()
    }
}