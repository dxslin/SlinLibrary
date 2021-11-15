package com.slin.score.mvi

import android.util.Log
import kotlinx.coroutines.delay
import java.util.*


/**
 * author: slin
 * date: 2021/11/15
 * description:
 *
 */

private val TAG = EmptyViewModel::class.java.simpleName

class EmptyViewModel : MviViewModel<EmptyViewState, EmptyAction>(EmptyViewState) {

    override fun onStart(register: ReducerRegister<EmptyViewState, EmptyAction>) {
        register[EmptyAction::class] = { viewState, action ->
            delay(1000)
            emit(EmptyViewState)
            Log.d(TAG, "1 onStart: ${Date()}")
            delay(2000)
            emit(EmptyViewState)
            Log.d(TAG, "2 onStart: ${Date()}")
            delay(3000)
            emit(EmptyViewState)
            Log.d(TAG, "3 onStart: ${Date()}")
        }
    }

}