package com.slin.test.di

import android.app.Application
import android.content.Context
import com.slin.core.config.CoreConfig
import com.slin.core.config.DefaultConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


/**
 * author: slin
 * date: 2020/9/10
 * description: app动态配置项，需要依赖注入的配置在这里
 *
 */

@Module
@InstallIn(SingletonComponent::class)
object ConfigModule {

    @Provides
    fun provideAppConfig(
            @ApplicationContext context: Context,
    ): CoreConfig {
        return CoreConfig(
                application = context as Application,
                baseUrl = DefaultConfig.BASE_URL,
                httpLogLevel = DefaultConfig.HTTP_LOG_LEVEL,
        )
    }

}
