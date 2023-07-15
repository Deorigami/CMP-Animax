package com.eyedea.shared_core.di

import com.eyedea.shared_core.base.BaseViewModel
import org.koin.core.module.Module

expect inline fun <reified R : BaseViewModel, reified T1> Module.sharedViewModelOf(crossinline constructor: (T1) -> R)
expect inline fun <reified R : BaseViewModel, reified T1, reified T2> Module.sharedViewModelOf(crossinline constructor: (T1, T2) -> R)
expect inline fun <reified R : BaseViewModel> Module.sharedViewModelOf(crossinline constructor: () -> R)