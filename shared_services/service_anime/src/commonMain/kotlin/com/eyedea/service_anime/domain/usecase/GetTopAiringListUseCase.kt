package com.eyedea.service_anime.domain.usecase

import com.eyedea.service_anime.domain.entity.AnimeShowcaseEntity
import com.eyedea.service_anime.domain.entity.AnimeShowcaseListEntity
import com.eyedea.service_anime.domain.repository.ServiceAnimeRepository
import com.eyedea.shared_core.base.BaseRespondEntity
import com.eyedea.shared_core.base.FlowUseCase
import kotlinx.coroutines.flow.Flow

class GetTopAiringListUseCase(
    private val repo : ServiceAnimeRepository
) : FlowUseCase<Unit, AnimeShowcaseListEntity>() {
    override val default: AnimeShowcaseListEntity
        get() = AnimeShowcaseListEntity.DEFAULT

    override suspend fun build(param: Unit): Flow<BaseRespondEntity<AnimeShowcaseListEntity>> {
        return repo.getTopAiringList()
    }
}