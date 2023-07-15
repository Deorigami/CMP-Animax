package com.eyedea.shared_core.di

import com.eyedea.shared_core.base.BaseViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf

actual inline fun <reified R : BaseViewModel, reified T1> Module.sharedViewModelOf(
    crossinline constructor: (T1) -> R,
) {
    singleOf(constructor)
}

actual inline fun <reified R : BaseViewModel, reified T1, reified T2> Module.sharedViewModelOf(
    crossinline constructor: (T1, T2) -> R,
) {
    singleOf(constructor)
}

actual inline fun <reified R : BaseViewModel> Module.sharedViewModelOf(
    crossinline constructor: () -> R,
) {
    singleOf(constructor)
}