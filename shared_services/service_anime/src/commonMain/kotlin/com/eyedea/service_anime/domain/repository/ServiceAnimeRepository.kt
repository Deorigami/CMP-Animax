package com.eyedea.service_anime.domain.repository

import com.eyedea.service_anime.domain.entity.TopRatedAnimeEntity
import com.eyedea.shared_core.base.BaseRespondEntity

interface ServiceAnimeRepository {
    suspend fun getTopHitsAnime() : BaseRespondEntity<List<TopRatedAnimeEntity>>
}