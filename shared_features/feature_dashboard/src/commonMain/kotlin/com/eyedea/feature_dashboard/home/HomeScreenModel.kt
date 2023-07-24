package com.eyedea.feature_dashboard.home

import com.eyedea.service_anime.domain.usecase.GetNewReleaseListUseCase
import com.eyedea.service_anime.domain.usecase.GetTopAiringListUseCase
import com.eyedea.shared_core.base.BaseViewModel
import com.eyedea.shared_core.base.StatefulData
import com.eyedea.shared_core.base.StatefulFlow
import kotlinx.coroutines.flow.MutableStateFlow

class HomeScreenModel(
    getPopularAnimeListUseCase: GetTopAiringListUseCase,
    getNewReleaseListUseCase: GetNewReleaseListUseCase
) : BaseViewModel() {
    val popularAnime = StatefulFlow(
        getPopularAnimeListUseCase,
        coroutineScope
    )
    val newRelease = StatefulFlow(
        getNewReleaseListUseCase,
        coroutineScope
    )

    fun initData(){
        popularAnime.loadData(Unit)
        newRelease.loadData(Unit)
    }

    override fun getStatefulData(): List<StatefulData<*, *>> {
        return listOf()
    }
}