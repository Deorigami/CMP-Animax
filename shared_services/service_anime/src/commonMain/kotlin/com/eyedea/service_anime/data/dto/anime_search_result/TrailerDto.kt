package com.eyedea.service_anime.data.dto.anime_search_result

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TrailerDto(
    @SerialName("embed_url")
    val embedUrl: String? = null,
    val images: TrailerImageDto,
    val url: String? = null,
    @SerialName("youtube_id")
    val youtubeId: String? = null
)