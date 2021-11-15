package com.slin.score.mvi

import kotlin.reflect.KClass

/**
 * author: slin
 * date: 2021/11/15
 * description:
 *
 */
class ReducerRegister<S : ViewState, A : Action> {

    private val map: MutableMap<KClass<out A>, Reducer<S, A>> = mutableMapOf()

    fun addReducer(action: KClass<out A>, reducer: Reducer<S, A>) {
        if (map.containsKey(action)) {
            error("It has already registered $action.")
        }
        map[action] = reducer
    }

    operator fun set(action: KClass<out A>, reducer: Reducer<S, A>) {
        addReducer(action, reducer)
    }

    internal operator fun get(action: KClass<out A>): Reducer<S, A> {
        return map[action] ?: error("Cannot find reducer with $action")
    }

}

