package com.eyedea.service_anime.domain.usecase

import com.eyedea.service_anime.domain.entity.AnimeShowcaseEntity
import com.eyedea.service_anime.domain.repository.ServiceAnimeRepository
import com.eyedea.shared_core.base.BaseRespondEntity
import com.eyedea.shared_core.base.BaseUseCase
import com.eyedea.shared_core.base.FlowUseCase
import kotlinx.coroutines.flow.Flow

class GetNewReleaseListUseCase(
    private val repo : ServiceAnimeRepository
) : FlowUseCase<Unit, List<AnimeShowcaseEntity>>() {
    override val default: List<AnimeShowcaseEntity>
        get() = emptyList()

    override suspend fun build(param: Unit): Flow<BaseRespondEntity<List<AnimeShowcaseEntity>>> {
        return repo.getNewReleaseList()
    }
}