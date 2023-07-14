package com.eyedea.shared_core.di

import com.eyedea.shared_core.base.BaseViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module

actual inline fun <reified R : BaseViewModel, reified T1> Module.sharedViewModelOf(
    crossinline constructor: (T1) -> R,
) {
    viewModelOf(constructor)
}

actual inline fun <reified R : BaseViewModel> Module.sharedViewModelOf(
    crossinline constructor: () -> R,
) {
    viewModelOf(constructor)
}

actual inline fun <reified R : BaseViewModel, reified T1, reified T2> Module.sharedViewModelOf(crossinline constructor: (T1, T2) -> R) {
    viewModelOf(constructor)
}