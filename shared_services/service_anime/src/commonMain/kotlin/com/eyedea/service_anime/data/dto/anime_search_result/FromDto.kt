package com.eyedea.service_anime.data.dto.anime_search_result

import kotlinx.serialization.Serializable

@Serializable
data class FromDto(
    val day: Int,
    val month: Int,
    val year: Int
)