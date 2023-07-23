package com.eyedea.service_anime.data.dto.anify

import kotlinx.serialization.Serializable


@Serializable
data class AnimeDetailDto(
    val artwork: List<Artwork>,
    val averagePopularity: String,
    val averageRating: String,
    val bannerImage: String,
    val chapters: Chapters? = null,
    val characters: List<Character>? = null,
    val color: String? = null,
    val countryOfOrigin: String,
    val coverImage: String,
    val currentEpisode: Int? = null,
    val description: String,
    val duration: Long? = null,
    val episodes: Episodes,
    val format: String,
    val genres: List<String>,
    val id: String,
    val mappings: List<Mapping>,
    val popularity: Popularity,
    val rating: Rating,
    val relations: List<Relation>? = null,
    val season: String,
    val slug: String,
    val status: String,
    val synonyms: List<String>,
    val tags: List<String>,
    val title: TitleX,
    val totalChapters: Int? = null,
    val totalEpisodes: Int? = null,
    val totalVolumes: Int? = null,
    val trailer: String? = null,
    val type: String,
    val year: Int? = null
)

@Serializable
data class Artwork(
    val img: String,
    val providerId: String,
    val type: String
)

@Serializable
data class Chapters(
    val `data`: List<Data>,
    val latest: Latest
)

@Serializable
data class Character(
    val image: String? = null,
    val name: String? = null,
    val voiceActor: VoiceActor
)

@Serializable
data class Episodes(
    val `data`: List<DataX>,
    val latest: LatestX
)

@Serializable
data class Mapping(
    val id: String,
    val providerId: String,
    val providerType: String,
    val similarity: String
)

@Serializable
data class Popularity(
    val anilist: Double? = null,
    val kitsu: Double? = null,
    val mal: Double? = null,
    val tvdb: Double? = null
)

@Serializable
data class Rating(
    val anilist: Double? = null,
    val kitsu: Double? = null,
    val mal: Double? = null,
    val tvdb: Double? = null
)

@Serializable
data class Relation(
    val `data`: DataXX? = null,
    val id: String,
    val type: String
)

@Serializable
data class TitleX(
    val english: String? = null,
    val native: String? = null,
    val romaji: String? = null
)

@Serializable
data class Data(
    val chapters: List<Chapter>? = null,
    val providerId: String
)

@Serializable
data class Latest(
    val latestChapter: String,
    val latestTitle: String,
    val updatedAt: String
)

@Serializable
data class Chapter(
    val id: String,
    val number: String,
    val title: String,
    val updatedAt: String
)

@Serializable
data class VoiceActor(
    val image: String? = null,
    val name: String? = null
)

@Serializable
data class DataX(
    val episodes: List<Episode>,
    val providerId: String
)

@Serializable
data class LatestX(
    val latestEpisode: String,
    val latestTitle: String,
    val updatedAt: String
)

@Serializable
data class Episode(
    val hasDub: String,
    val id: String,
    val img: String? = null,
    val isFiller: String,
    val number: String,
    val title: String,
    val updatedAt: String
)

@Serializable
data class DataXX(
    val bannerImage: String? = null,
    val coverImage: CoverImage,
    val format: String,
    val id: String,
    val status: String,
    val title: Title,
    val type: String
)

@Serializable
data class CoverImage(
    val large: String
)

@Serializable
data class Title(
    val userPreferred: String
)