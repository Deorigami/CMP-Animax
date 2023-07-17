package com.eyedea.service_anime.data.dto.anime_search_result

import kotlinx.serialization.Serializable

@Serializable
data class AiredDto(
    val from: String,
    val prop: PropDto,
    val string: String,
    val to: String? = null
)