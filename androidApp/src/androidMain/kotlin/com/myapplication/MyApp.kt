package com.myapplication

import BaseApp
import com.eyedea.feature_dashboard.featureDashboardModule
import com.eyedea.feature_detail.featureDetailModule
import com.eyedea.service_anime.serviceAnimeModule
import com.eyedea.shared_ui_components.util.CommonImageLoaderImpl
import di.initKoin
import di.routerModule

class MyApp : BaseApp() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
        CommonImageLoaderImpl.applicationContext = this
    }
}