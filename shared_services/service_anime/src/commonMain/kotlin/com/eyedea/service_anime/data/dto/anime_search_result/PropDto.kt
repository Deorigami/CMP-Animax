package com.eyedea.service_anime.data.dto.anime_search_result

import kotlinx.serialization.Serializable

@Serializable
data class PropDto(
    val from: FromDto,
    val to: ToDto
)