package com.slin.core.di

import com.google.gson.Gson
import com.slin.core.config.CoreConfig
import com.slin.core.image.ImageLoader
import com.slin.core.repository.Preference
import com.slin.core.repository.SCorePreferenceQualifier
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit


/**
 * author: slin
 * date: 2020/11/27
 * description: 可以共享出去的注入对象
 *
 */
@EntryPoint
@InstallIn(SingletonComponent::class)
interface CoreComponentDependencies {

    fun coreConfig(): CoreConfig

    fun retrofit(): Retrofit

    @OkHttpClientQualifier
    fun okHttpClient(): OkHttpClient

    @ImageOkHttpClientQualifier
    fun imageOkHttpClient(): OkHttpClient

    fun imageLoader(): ImageLoader

    @SCorePreferenceQualifier
    fun coreSharedPreference(): Preference

    fun gson(): Gson

}

