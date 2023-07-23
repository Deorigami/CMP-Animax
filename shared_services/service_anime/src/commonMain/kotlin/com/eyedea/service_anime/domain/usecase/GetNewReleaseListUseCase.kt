package com.eyedea.service_anime.domain.usecase

import com.eyedea.service_anime.domain.entity.AnimeShowcaseEntity
import com.eyedea.service_anime.domain.repository.ServiceAnimeRepository
import com.eyedea.shared_core.base.BaseRespondEntity
import com.eyedea.shared_core.base.BaseUseCase

class GetNewReleaseListUseCase(
    private val repo : ServiceAnimeRepository
) : BaseUseCase<Unit, List<AnimeShowcaseEntity>>() {
    override val default: List<AnimeShowcaseEntity>
        get() = emptyList()

    override suspend fun build(param: Unit): BaseRespondEntity<List<AnimeShowcaseEntity>> {
        return repo.getNewReleaseList()
    }
}