package com.eyedea.service_anime.data.dto.anime_search_result

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TrailerImageDto(
    @SerialName("image_url")
    val imageUrl: String? = null,
    @SerialName("large_image_url")
    val largeImageUrl: String? = null,
    @SerialName("maximum_image_url")
    val maximumImageUrl: String? = null,
    @SerialName("medium_image_url")
    val mediumImageUrl: String? = null,
    @SerialName("small_image_url")
    val smallImageUrl: String? = null
)