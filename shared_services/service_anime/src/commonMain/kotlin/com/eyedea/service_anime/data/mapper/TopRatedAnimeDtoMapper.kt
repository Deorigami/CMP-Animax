package com.eyedea.service_anime.data.mapper

import com.eyedea.service_anime.data.dto.anime_search_result.AnimeSearchResultDto
import com.eyedea.service_anime.domain.entity.TopRatedAnimeEntity

class TopRatedAnimeDtoMapper {
    operator fun invoke(from : AnimeSearchResultDto) : TopRatedAnimeEntity {
        return TopRatedAnimeEntity(
            animeId = from.malId,
            animeImg = from.images.jpg.imageUrl,
            animeTitle = from.title,
            animeUrl = from.url,
            releasedDate = from.aired.string,
            rating = from.score?.toString() ?: "0.0"
        )
    }
}