package com.eyedea.service_anime.data.dto.anime_search_result

import kotlinx.serialization.Serializable

@Serializable
data class ImagesDto(
    val jpg: JpgDto,
    val webp: WebpDto
)