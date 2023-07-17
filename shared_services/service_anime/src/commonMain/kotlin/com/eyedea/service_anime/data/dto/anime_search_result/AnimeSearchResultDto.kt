package com.eyedea.service_anime.data.dto.anime_search_result

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class AnimeSearchResultDto(
    val aired: AiredDto,
    val airing: Boolean,
    val approved: Boolean,
    val episodes: Int,
    val images: ImagesDto,
    @SerialName("mal_id")
    val malId: Int,
    val source: String,
    val status: String,
    val title: String,
    @SerialName("title_english")
    val titleEnglish: String,
    @SerialName("title_japanese")
    val titleJapanese: String,
    @SerialName("title_synonyms")
    val titleSynonyms: List<String>,
    val titles: List<TitleDto>,
    val trailer: TrailerDto,
    val type: String,
    val url: String,
    val score: Double? = null,
)


















