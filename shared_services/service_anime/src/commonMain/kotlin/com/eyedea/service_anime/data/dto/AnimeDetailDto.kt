package com.eyedea.service_anime.data.dto


import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@Serializable
data class AnimeDetailDto @OptIn(ExperimentalSerializationApi::class) constructor(
    val description: String? = null,
    val episodes: List<EpisodeDto>,
    val genres: List<String>,
    val id: String,
    val image: String,
    val otherName: String? = null,
    @JsonNames("releaseDate", "releaseYear") val releaseDate: String? = null,
    val status: String,
    val subOrDub: String,
    val title: String,
    val totalEpisodes: Int? = null,
    val type: String? = null,
    val url: String
)