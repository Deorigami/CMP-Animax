package com.eyedea.shared_ui_components.util

import com.seiko.imageloader.component.ComponentRegistryBuilder
import com.seiko.imageloader.component.setupDefaultComponents
import io.ktor.client.HttpClient
import okio.Path
import okio.Path.Companion.toPath


actual object CommonImageLoaderImpl : CommonImageLoader {
    override fun ComponentRegistryBuilder.setupDefaultComponents() {
        setupDefaultComponents { HttpClient() }
    }

    override fun getImageCacheDirectoryPath(): Path = "media/".toPath()
}