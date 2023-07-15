package com.eyedea.shared_ui_components.util

import com.eyedea.shared_ui_components.util.CommonImageLoaderImpl.setupDefaultComponents
import com.seiko.imageloader.ImageLoader
import com.seiko.imageloader.component.ComponentRegistryBuilder
import okio.Path

interface CommonImageLoader {
    fun ComponentRegistryBuilder.setupDefaultComponents()
    fun getImageCacheDirectoryPath(): Path
}

expect object CommonImageLoaderImpl : CommonImageLoader

fun generateImageLoader(
    memCacheSize: Int = 32 * 1024 * 1024, //32MB
    diskCacheSize: Int = 512 * 1024 * 1024 //512MB
) = ImageLoader {
    interceptor {
        memoryCacheConfig {
            maxSizeBytes(memCacheSize)
        }
        diskCacheConfig {
            directory(CommonImageLoaderImpl.getImageCacheDirectoryPath())
            maxSizeBytes(diskCacheSize.toLong())
        }
    }
    components {
        setupDefaultComponents()
    }
}