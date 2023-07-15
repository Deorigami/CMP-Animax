package com.eyedea.service_anime.data.repository

import com.eyedea.service_anime.data.dto.TopAnimeDto
import com.eyedea.service_anime.data.mapper.TopAnimeDtoMapper
import com.eyedea.service_anime.domain.entity.TopAnimeEntity
import com.eyedea.service_anime.domain.repository.ServiceAnimeRepository
import com.eyedea.shared_core.base.BaseRespondEntity
import com.eyedea.shared_core.base.KtorNetworkProvider
import com.eyedea.shared_core.base.dtoToBaseRespondEntity
import io.ktor.client.request.get

class ServiceAnimeRepositoryImpl(
    private val animeDtoMapper: TopAnimeDtoMapper
) : ServiceAnimeRepository {

    private val client get() = KtorNetworkProvider.client

    override suspend fun getTopHitsAnime(): BaseRespondEntity<List<TopAnimeEntity>> {
        return client
            .get("popular")
            .dtoToBaseRespondEntity<List<TopAnimeDto>, List<TopAnimeEntity>> {
                topAnimeDtos -> topAnimeDtos
                .map { animeDtoMapper(it) }
            }
    }
}