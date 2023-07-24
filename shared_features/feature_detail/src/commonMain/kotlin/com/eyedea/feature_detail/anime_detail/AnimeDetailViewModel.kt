package com.eyedea.feature_detail.anime_detail

import com.eyedea.service_anime.domain.usecase.GetAnimeDetailUseCase
import com.eyedea.shared_core.base.BaseViewModel
import com.eyedea.shared_core.base.StatefulData
import com.eyedea.shared_core.base.StatefulFlow

class AnimeDetailViewModel(
    getAnimeDetailUseCase: GetAnimeDetailUseCase
) : BaseViewModel() {
    override fun getStatefulData(): List<StatefulData<*, *>> {
        return listOf()
    }
    val animeDetail = StatefulFlow(
        getAnimeDetailUseCase,
        coroutineScope
    )
}