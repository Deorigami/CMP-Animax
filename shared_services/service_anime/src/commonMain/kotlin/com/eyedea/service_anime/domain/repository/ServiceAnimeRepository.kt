package com.eyedea.service_anime.domain.repository

import com.eyedea.service_anime.domain.entity.AnimeDetailEntity
import com.eyedea.service_anime.domain.entity.AnimeShowcaseEntity
import com.eyedea.service_anime.domain.entity.AnimeShowcaseListEntity
import com.eyedea.shared_core.base.BaseRespondEntity
import kotlinx.coroutines.flow.Flow

interface ServiceAnimeRepository {
    suspend fun getTopAiringList() : Flow<BaseRespondEntity<AnimeShowcaseListEntity>>
    suspend fun getNewReleaseList() : BaseRespondEntity<List<AnimeShowcaseEntity>>
    suspend fun getAnimeDetail(id : String) : BaseRespondEntity<AnimeDetailEntity>
}