package com.eyedea.service_anime.data.dto.anime_search_result

import kotlinx.serialization.Serializable

@Serializable
data class ToDto(
    val day: Int? = null,
    val month: Int? = null,
    val year: Int? = null
)