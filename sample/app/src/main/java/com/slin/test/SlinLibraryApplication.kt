package com.slin.test

import android.app.Application
import com.slin.core.SCore
import com.slin.core.logger.initLogger
import com.slin.svs.StateViewSwitcher
import com.slin.svs.ext.CoreStateViewFactory
import dagger.hilt.android.HiltAndroidApp


/**
 * author: slin
 * date: 2021/1/28
 * description:
 *
 */
@HiltAndroidApp
class SlinLibraryApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        SCore.init(this)
        SCore.initLogger(BuildConfig.DEBUG)
        StateViewSwitcher.config(CoreStateViewFactory())

    }

}