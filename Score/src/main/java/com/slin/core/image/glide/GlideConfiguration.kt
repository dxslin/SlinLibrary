package com.slin.core.image.glide

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import com.slin.core.SCore
import com.slin.core.config.CoreConfig
import com.slin.core.image.ApplyGlideOptions
import java.io.File
import java.io.InputStream

/**
 * author: slin
 * date: 2019-04-29
 * description:
 */
@GlideModule(glideName = "GlideGit")
class GlideConfiguration : AppGlideModule() {

    private val okHttpClient = SCore.coreComponent.imageOkHttpClient()

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        val coreConfig: CoreConfig = SCore.coreComponent.coreConfig()
        builder.setDiskCache {
            DiskLruCacheWrapper.create(
                    File(coreConfig.cacheFile, "Glide"),
                    IMAGE_DISK_CACHE_MAX_SIZE.toLong()
            )
        }
        val calculator = MemorySizeCalculator.Builder(context).build()
        val defaultMemoryCacheSize = calculator.memoryCacheSize
        val defaultBitmapPoolSize = calculator.bitmapPoolSize
        val customMemoryCacheSize = (1.2 * defaultMemoryCacheSize).toInt()
        val customBitmapPoolSize = (1.2 * defaultBitmapPoolSize).toInt()
        builder.setMemoryCache(
                LruResourceCache(
                        customMemoryCacheSize.toLong()
                )
        )
        builder.setBitmapPool(
                LruBitmapPool(
                        customBitmapPoolSize.toLong()
                )
        )

        //自定义Glide的配置
        val applyGlideOptions: ApplyGlideOptions? = coreConfig.glideOptions
        applyGlideOptions?.applyGlideOptions(context, builder)
    }

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        //使用OkHttp替换掉Glide原有的HttpURLConnection做网络请求
        registry.replace(
                GlideUrl::class.java,
                InputStream::class.java,
                OkHttpGlideUrlLoader.Factory(okHttpClient)
        )
    }

    override fun isManifestParsingEnabled(): Boolean {
        return false
    }

    companion object {
        private const val IMAGE_DISK_CACHE_MAX_SIZE = 100 * 1024 * 1024 //图片缓存文件最多能存100MB
    }

}