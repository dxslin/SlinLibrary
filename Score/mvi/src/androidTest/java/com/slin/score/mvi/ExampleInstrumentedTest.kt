package com.slin.score.mvi

import androidx.lifecycle.viewModelScope
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.slin.score.mvi.test", appContext.packageName)

        val emptyViewModel = EmptyViewModel()
        emptyViewModel.viewModelScope.launch {
            emptyViewModel.viewState.collectLatest {
                println(it)
            }
        }

        emptyViewModel.dispatch(EmptyAction) {
            println("result ${emptyViewModel.viewState.value}")
        }

        Thread.sleep(10000)

    }
}

