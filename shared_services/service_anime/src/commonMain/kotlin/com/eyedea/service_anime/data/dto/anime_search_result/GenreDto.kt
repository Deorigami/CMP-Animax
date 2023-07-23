package com.eyedea.service_anime.data.dto.anime_search_result

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenreDto(
    @SerialName("mal_id") val malId : Int,
    val type : String,
    val name : String,
    val url : String
)
