package com.eyedea.service_anime.data.dto.anime_search_result

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JpgDto(
    @SerialName("image_url")
    val imageUrl: String,
    @SerialName("large_image_url")
    val largeImageUrl: String,
    @SerialName("small_image_url")
    val smallImageUrl: String
)