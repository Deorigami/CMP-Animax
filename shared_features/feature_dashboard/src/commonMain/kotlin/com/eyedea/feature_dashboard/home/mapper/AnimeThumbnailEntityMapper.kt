package com.eyedea.feature_dashboard.home.mapper

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import com.eyedea.service_anime.domain.entity.AnimeShowcaseEntity
import com.eyedea.shared_ui_components.composables.HomeRowListData

class AnimeThumbnailEntityMapper {
    operator fun invoke(from : List<AnimeShowcaseEntity>?) : SnapshotStateList<HomeRowListData> {
        return from?.map { HomeRowListData(
            image = it.animeImg,
            title = it.animeTitle,
            index = "",
            rating = it.rating
        ) }?.toMutableStateList() ?: mutableStateListOf()
    }
}