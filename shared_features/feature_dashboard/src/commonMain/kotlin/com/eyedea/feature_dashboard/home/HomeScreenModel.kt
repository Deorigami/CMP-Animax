package com.eyedea.feature_dashboard.home

import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.eyedea.service_anime.domain.usecase.GetPopularAnimeListUseCase
import com.eyedea.shared_core.base.BaseViewModel
import com.eyedea.shared_core.base.StatefulData
import kotlinx.coroutines.flow.MutableStateFlow

class HomeScreenModel(
    getPopularAnimeListUseCase: GetPopularAnimeListUseCase
) : BaseViewModel() {
    val popularAnime = StatefulData(
        getPopularAnimeListUseCase,
        coroutineScope
    )
    val pressedAnimeDetailId = MutableStateFlow("")

    init {
        popularAnime.loadData(Unit)
    }

    override fun getStatefulData(): List<StatefulData<*, *>> {
        return listOf(popularAnime)
    }
}