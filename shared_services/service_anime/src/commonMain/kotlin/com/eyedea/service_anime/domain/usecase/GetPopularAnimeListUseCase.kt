package com.eyedea.service_anime.domain.usecase

import com.eyedea.service_anime.domain.entity.TopRatedAnimeEntity
import com.eyedea.service_anime.domain.repository.ServiceAnimeRepository
import com.eyedea.shared_core.base.BaseRespondEntity
import com.eyedea.shared_core.base.BaseUseCase

class GetPopularAnimeListUseCase(
    private val repo : ServiceAnimeRepository
) : BaseUseCase<Unit, List<TopRatedAnimeEntity>>() {
    override val default: List<TopRatedAnimeEntity>
        get() = emptyList()

    override suspend fun build(param: Unit): BaseRespondEntity<List<TopRatedAnimeEntity>> {
        return repo.getTopHitsAnime()
    }
}