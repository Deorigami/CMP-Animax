package com.eyedea.service_anime.data.repository

import com.eyedea.service_anime.data.dto.anime_search_result.AnimeSearchResultDto
import com.eyedea.service_anime.data.mapper.TopRatedAnimeDtoMapper
import com.eyedea.service_anime.domain.entity.TopRatedAnimeEntity
import com.eyedea.service_anime.domain.repository.ServiceAnimeRepository
import com.eyedea.shared_core.base.BaseRespondEntity
import com.eyedea.shared_core.base.KtorNetworkProvider
import com.eyedea.shared_core.base.dtoToBaseRespondEntity
import com.eyedea.shared_core.base.toBaseRespondEntity
import io.ktor.client.request.get

class ServiceAnimeRepositoryImpl(
    private val topRatedAnimeDtoMapper: TopRatedAnimeDtoMapper
) : ServiceAnimeRepository {

    private val client get() = KtorNetworkProvider.client

    override suspend fun getTopHitsAnime(): BaseRespondEntity<List<TopRatedAnimeEntity>> {
        return client
            .get("anime?sfw=true&page=1&limit=10&order_by=score&sort=desc")
            .toBaseRespondEntity<List<AnimeSearchResultDto>, List<TopRatedAnimeEntity>> {
                topAnimeDtos -> topAnimeDtos
                .map { topRatedAnimeDtoMapper(it) }
            }
    }
}