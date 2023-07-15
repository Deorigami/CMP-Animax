package com.eyedea.feature_dashboard.home

import com.eyedea.service_anime.domain.usecase.GetPopularAnimeListUseCase
import com.eyedea.shared_core.base.BaseViewModel
import com.eyedea.shared_core.base.StatefulData

class HomeScreenModel(
    getPopularAnimeListUseCase: GetPopularAnimeListUseCase
) : BaseViewModel() {
    val popularAnime = StatefulData(
        getPopularAnimeListUseCase,
        scope
    )

    init {
        popularAnime.loadData(Unit)
    }

    override fun getStatefulData(): List<StatefulData<*, *>> {
        return listOf(popularAnime)
    }
}