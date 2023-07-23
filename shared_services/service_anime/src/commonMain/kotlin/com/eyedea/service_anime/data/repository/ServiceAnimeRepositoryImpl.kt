package com.eyedea.service_anime.data.repository

import com.eyedea.service_anime.data.dto.anify.AnimeDetailDto
import com.eyedea.service_anime.data.dto.anify.SeasonalAnimeDto
import com.eyedea.service_anime.data.dto.anify.SeasonalAnimeDto.Companion.toAnimeShowCaseListEntity
import com.eyedea.service_anime.data.mapper.TopRatedAnimeDtoMapper
import com.eyedea.service_anime.domain.entity.AnimeDetailEntity
import com.eyedea.service_anime.domain.entity.AnimeDetailEpisodeEntity
import com.eyedea.service_anime.domain.entity.AnimeShowcaseEntity
import com.eyedea.service_anime.domain.entity.AnimeShowcaseListEntity
import com.eyedea.service_anime.domain.repository.ServiceAnimeRepository
import com.eyedea.shared_core.base.BaseRespondEntity
import com.eyedea.shared_core.base.KtorNetworkProvider
import com.eyedea.shared_core.base.dtoToBaseRespondEntity
import com.eyedea.shared_core.extensions.tryDeserialize
import com.eyedea.shared_core.util.SessionManager
import io.github.aakira.napier.Napier
import io.ktor.client.request.get
import io.ktor.client.request.header
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ServiceAnimeRepositoryImpl(
    private val topRatedAnimeDtoMapper: TopRatedAnimeDtoMapper,
) : ServiceAnimeRepository {

    private val client get() = KtorNetworkProvider.client

    override suspend fun getTopAiringList(): Flow<BaseRespondEntity<AnimeShowcaseListEntity>> = flow {
        val url = "https://api.anify.tv/seasonal?type=anime&page=1&apikey=fae494e3b9e506ccd74586c128c58f50"
        val cache = SessionManager.get<String>(url)
        val cacheDto = cache?.let { tryDeserialize<SeasonalAnimeDto>(it) }
        cache?.let {
            val entity = cacheDto?.toAnimeShowCaseListEntity()
            emit(BaseRespondEntity(entity))
        }
        val fetchData = client
            .get(url){
                header("cached", true)
            }
            .dtoToBaseRespondEntity<SeasonalAnimeDto, AnimeShowcaseListEntity> { seasonalAnimeDto ->
                seasonalAnimeDto.toAnimeShowCaseListEntity()
            }
        emit(fetchData)

    }

    override suspend fun getNewReleaseList(): BaseRespondEntity<List<AnimeShowcaseEntity>> {
        return client
            .get("recent?type=anime&page=1&apikey=${SessionManager.API_KEY}"){
                header("cached", true)
            }
            .dtoToBaseRespondEntity<List<AnimeDetailDto>, List<AnimeShowcaseEntity>> { topAnimeDtos ->
                topAnimeDtos
                    .map {
                        AnimeShowcaseEntity(
                            animeId = it.id,
                            animeImg = it.coverImage,
                            animeTitle = it.title.english ?: "",
                            animeUrl = "",
                            releasedDate = it.year?.toString() ?: "",
                            rating = it.averageRating,
                            genres = it.genres.joinToString(","),
                        )
                    }
            }
    }

    override suspend fun getAnimeDetail(id: String): BaseRespondEntity<AnimeDetailEntity> {
        val response = client.get("https://api.anify.tv/info/$id?apikey=${SessionManager.API_KEY}")
            .dtoToBaseRespondEntity<com.eyedea.service_anime.data.dto.anify.AnimeDetailDto, AnimeDetailEntity> { animeDetailDto ->
                val episodeProvider = animeDetailDto.episodes.data.firstOrNull { it.episodes.any { !it.img.isNullOrEmpty() } }
                    ?: animeDetailDto.episodes.data.firstOrNull()
                AnimeDetailEntity(
                    id = animeDetailDto.id,
                    image = animeDetailDto.artwork.firstOrNull { it.type == "poster" }?.img ?: animeDetailDto.bannerImage,
                    title = animeDetailDto.title.english ?: "",
                    rating = animeDetailDto.averageRating,
                    yearProduced = animeDetailDto.year?.toString() ?: "",
                    genre = animeDetailDto.genres.joinToString(","),
                    episodes = episodeProvider.let { data ->
                        Napier.d(tag = "ANGGATAG") { "${data?.providerId} ${data?.episodes?.map { it.img }}" }
                        data?.episodes?.sortedByDescending { it.number.toIntOrNull() }?.map { episode ->
                            AnimeDetailEpisodeEntity(
                                image = episode.img.takeIf { !it.isNullOrEmpty() } ?: animeDetailDto.coverImage,
                                id = episode.id,
                                title = "Episode ${episode.number}"
                            )
                        }
                    } ?: emptyList(),
                    synopsis = animeDetailDto.description
                )
            }

        return response
    }
}