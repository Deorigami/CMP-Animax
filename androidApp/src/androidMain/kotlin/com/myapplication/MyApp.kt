package com.myapplication

import BaseApp
import com.eyedea.shared_ui_components.util.CommonImageLoaderImpl

class MyApp : BaseApp() {
    override fun onCreate() {
        super.onCreate()
        CommonImageLoaderImpl.applicationContext = this
    }
}