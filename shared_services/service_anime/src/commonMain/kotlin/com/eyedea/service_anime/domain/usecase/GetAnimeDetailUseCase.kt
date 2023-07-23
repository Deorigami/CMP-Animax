package com.eyedea.service_anime.domain.usecase

import com.eyedea.service_anime.domain.entity.AnimeDetailEntity
import com.eyedea.service_anime.domain.repository.ServiceAnimeRepository
import com.eyedea.shared_core.base.BaseRespondEntity
import com.eyedea.shared_core.base.BaseUseCase

class GetAnimeDetailUseCase(
    private val repo : ServiceAnimeRepository
) : BaseUseCase<String, AnimeDetailEntity>() {
    override val default: AnimeDetailEntity
        get() = AnimeDetailEntity.DEFAULT

    override suspend fun build(param: String): BaseRespondEntity<AnimeDetailEntity> {
        return repo.getAnimeDetail(param)
    }
}