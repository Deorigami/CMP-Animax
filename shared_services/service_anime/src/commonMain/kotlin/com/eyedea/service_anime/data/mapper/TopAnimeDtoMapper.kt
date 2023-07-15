package com.eyedea.service_anime.data.mapper

import com.eyedea.service_anime.data.dto.TopAnimeDto
import com.eyedea.service_anime.domain.entity.TopAnimeEntity

class TopAnimeDtoMapper {
    operator fun invoke(from : TopAnimeDto) : TopAnimeEntity {
        return TopAnimeEntity(
            animeId = from.animeId,
            animeImg = from.animeImg,
            animeTitle = from.animeTitle,
            animeUrl = from.animeUrl,
            releasedDate = from.releasedDate
        )
    }
}