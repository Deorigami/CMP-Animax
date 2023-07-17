package di

import com.eyedea.feature_detail.anime_detail.AnimeDetailScreen
import com.eyedea.feature_util.router.GlobalRouter
import com.eyedea.shared_core.base.BaseRouter

abstract class GlobalRouterImpl : BaseRouter(), GlobalRouter {
    override fun navigateToAnimeDetail(id: Int) {
        push(AnimeDetailScreen(id))
    }
}