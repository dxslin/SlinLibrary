package com.slin.core.di

import android.app.Application
import android.content.Context
import com.slin.core.repository.Preference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton


/**
 * author: slin
 * date: 2020/9/3
 * description: 数据层注入
 *
 */

const val DEFAULT_SHARE_PREFERENCES_TAG = "score_share_preferences"

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    @SCorePreferenceQualifier
    fun provideSharedPreferences(@SCoreApplicationQualifier application: Application): Preference {
        return application.getSharedPreferences(DEFAULT_SHARE_PREFERENCES_TAG, Context.MODE_PRIVATE)
    }


}


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SCorePreferenceQualifier
