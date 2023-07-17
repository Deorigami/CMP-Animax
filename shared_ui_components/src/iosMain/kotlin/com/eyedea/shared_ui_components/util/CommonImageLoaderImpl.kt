package com.eyedea.shared_ui_components.util

import com.seiko.imageloader.component.ComponentRegistryBuilder
import okio.Path
import okio.Path.Companion.toPath
import platform.Foundation.NSCachesDirectory
import platform.Foundation.NSSearchPathForDirectoriesInDomains
import platform.Foundation.NSUserDomainMask

actual object CommonImageLoaderImpl : CommonImageLoader {
    override fun ComponentRegistryBuilder.setupDefaultComponents() {
        this.setupDefaultComponents()
    }

    override fun getImageCacheDirectoryPath(): Path {
        val cacheDir = NSSearchPathForDirectoriesInDomains(
            NSCachesDirectory,
            NSUserDomainMask,
            true
        ).first() as String
        return ("$cacheDir/media").toPath()
    }
}