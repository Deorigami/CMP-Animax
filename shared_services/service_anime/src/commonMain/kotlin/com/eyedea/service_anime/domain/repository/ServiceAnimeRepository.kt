package com.eyedea.service_anime.domain.repository

import com.eyedea.service_anime.domain.entity.TopAnimeEntity
import com.eyedea.shared_core.base.BaseRespondEntity

interface ServiceAnimeRepository {
    suspend fun getTopHitsAnime() : BaseRespondEntity<List<TopAnimeEntity>>
}