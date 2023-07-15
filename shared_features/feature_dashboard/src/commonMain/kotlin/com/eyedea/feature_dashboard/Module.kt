package com.eyedea.feature_dashboard

import com.eyedea.feature_dashboard.home.HomeScreenModel
import com.eyedea.shared_core.di.sharedViewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

fun featureDashboardModule() = listOf(
    viewModelModule()
)

private fun viewModelModule() = module {
    singleOf(::HomeScreenModel)
}