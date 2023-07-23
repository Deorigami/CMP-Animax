package com.eyedea.feature_detail

import com.eyedea.feature_detail.anime_detail.AnimeDetailViewModel
import com.eyedea.shared_core.di.sharedViewModelOf
import org.koin.dsl.module

fun featureDetailModule() = listOf(
    viewModelModule()
)
private fun viewModelModule() = module {
    sharedViewModelOf(::AnimeDetailViewModel)
}