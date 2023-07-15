package com.eyedea.shared_ui_components.util

import android.content.Context
import com.seiko.imageloader.component.ComponentRegistryBuilder
import com.seiko.imageloader.component.setupDefaultComponents
import okio.Path
import okio.Path.Companion.toPath

actual object CommonImageLoaderImpl : CommonImageLoader {

    lateinit var applicationContext : Context

    override fun ComponentRegistryBuilder.setupDefaultComponents() {
        setupDefaultComponents(applicationContext)
    }

    override fun getImageCacheDirectoryPath(): Path {
        return applicationContext.cacheDir.absolutePath.toPath()
    }
}