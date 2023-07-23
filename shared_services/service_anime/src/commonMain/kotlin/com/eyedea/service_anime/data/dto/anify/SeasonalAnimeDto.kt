package com.eyedea.service_anime.data.dto.anify

import com.eyedea.service_anime.domain.entity.AnimeShowcaseEntity
import com.eyedea.service_anime.domain.entity.AnimeShowcaseListEntity
import kotlinx.serialization.Serializable

@Serializable
data class SeasonalAnimeDto(
    val trending : List<AnimeDetailDto>? = null,
    val popular : List<AnimeDetailDto>? = null,
    val top : List<AnimeDetailDto>? = null,
    val seasonal : List<AnimeDetailDto>? = null,
) {
    companion object {
        fun SeasonalAnimeDto.toAnimeShowCaseListEntity() : AnimeShowcaseListEntity {
            return AnimeShowcaseListEntity(
                trending = trending?.map { it.toAnimeShowCaseEntity() } ?: emptyList(),
                popular = trending?.map { it.toAnimeShowCaseEntity() } ?: emptyList(),
                top = trending?.map { it.toAnimeShowCaseEntity() } ?: emptyList(),
                seasonal = trending?.map { it.toAnimeShowCaseEntity() } ?: emptyList()
            )
        }

        private fun AnimeDetailDto.toAnimeShowCaseEntity() : AnimeShowcaseEntity = AnimeShowcaseEntity(
            animeId = id,
            animeImg = coverImage,
            animeTitle = title.english ?: "",
            animeUrl = mappings.first().id,
            releasedDate = year?.toString() ?: "",
            rating = averageRating,
            genres = genres.joinToString(","),
        )
    }
}
