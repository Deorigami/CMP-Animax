package com.eyedea.service_anime.data.repository

import com.eyedea.service_anime.data.dto.anify.AnimeDetailDto
import com.eyedea.service_anime.data.dto.anify.AnimeDetailDto.Companion.toAnimeDetailEntity
import com.eyedea.service_anime.data.dto.anify.AnimeDetailDto.Companion.toAnimeShowCaseEntity
import com.eyedea.service_anime.data.dto.anify.SeasonalAnimeDto
import com.eyedea.service_anime.data.dto.anify.SeasonalAnimeDto.Companion.toAnimeShowCaseListEntity
import com.eyedea.service_anime.data.mapper.TopRatedAnimeDtoMapper
import com.eyedea.service_anime.domain.entity.AnimeDetailEntity
import com.eyedea.service_anime.domain.entity.AnimeShowcaseEntity
import com.eyedea.service_anime.domain.entity.AnimeShowcaseListEntity
import com.eyedea.service_anime.domain.repository.ServiceAnimeRepository
import com.eyedea.shared_core.base.BaseRespondEntity
import com.eyedea.shared_core.base.KtorNetworkProvider
import com.eyedea.shared_core.base.dtoToBaseRespondEntity
import com.eyedea.shared_core.base.toFlow
import com.eyedea.shared_core.util.SessionManager
import io.ktor.client.request.get
import io.ktor.http.HttpMethod
import kotlinx.coroutines.flow.Flow

class ServiceAnimeRepositoryImpl : ServiceAnimeRepository {

    private val client get() = KtorNetworkProvider.client

    override suspend fun getTopAiringList(): Flow<BaseRespondEntity<AnimeShowcaseListEntity>> = client
        .toFlow<SeasonalAnimeDto, AnimeShowcaseListEntity>(
            url = "seasonal?type=anime&page=1&apikey=fae494e3b9e506ccd74586c128c58f50",
            method = HttpMethod.Get,
            getCache = true,
            block = { it.toAnimeShowCaseListEntity() }
        )

    override suspend fun getNewReleaseList(): Flow<BaseRespondEntity<List<AnimeShowcaseEntity>>> = client
        .toFlow<List<AnimeDetailDto>, List<AnimeShowcaseEntity>>(
            url = "recent?type=anime&page=1&apikey=${SessionManager.API_KEY}",
            method = HttpMethod.Get,
            getCache = true,
            block = { detailDtos -> detailDtos.map { it.toAnimeShowCaseEntity() } }
        )

    override suspend fun getAnimeDetail(id: String): Flow<BaseRespondEntity<AnimeDetailEntity>> = client
        .toFlow<AnimeDetailDto, AnimeDetailEntity>(
            "info/$id?apikey=${SessionManager.API_KEY}",
            method = HttpMethod.Get,
            getCache = true,
            block = { it.toAnimeDetailEntity() }
        )
}