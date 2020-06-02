package com.sunnyfor.zy.utils

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory
import com.bumptech.glide.module.AppGlideModule

/**
 *
 */
@GlideModule
class MyAppGlideModule : AppGlideModule(){
    private val path = FileUtils().getCacheDir().path
    override fun applyOptions(context: Context, builder: GlideBuilder) {
        super.applyOptions(context, builder)
        val diskCacheSizeBytes = 1024 * 1024 * 200L // 200 MB
        builder.setDiskCache(DiskLruCacheFactory(path,diskCacheSizeBytes))
    }
}