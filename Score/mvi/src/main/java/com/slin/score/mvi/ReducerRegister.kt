package com.slin.score.mvi

import kotlin.reflect.KClass

/**
 * author: slin
 *
 * date: 2021/11/15
 *
 * description: 执行器注册机
 *
 * 管理注册执行器
 *
 */
class ReducerRegister<S : ViewState, A : Action> {

    private val map: MutableMap<KClass<out A>, Reducer<S, A>> = mutableMapOf()

    /**
     * 添加对应于[action]的执行器[reducer]
     * @param action  行为对象
     * @param reducer 执行器
     */
    fun addReducer(action: KClass<out A>, reducer: Reducer<S, A>) {
        map.keys.forEach {
            // 判断是否重复添加，类本身或者超类都算
            if (action.equalActionClass(it)) {
                error("It has already registered $action.")
            }
        }
        map[action] = reducer
    }

    operator fun set(action: KClass<out A>, reducer: Reducer<S, A>) {
        addReducer(action, reducer)
    }

    internal operator fun get(action: KClass<out A>): Reducer<S, A> {
        return reducerOf(action)
    }

    /**
     * 获取对应于[action]的执行器
     */
    internal fun reducerOf(action: KClass<out A>): Reducer<S, A> {
        map.entries.forEach {
            if (action.equalActionClass(it.key)) {
                return it.value
            }
        }
        error("Cannot find reducer with $action")
    }

    private fun KClass<out Action>.equalActionClass(action: KClass<out A>): Boolean {
        return this == action || this.java.isAssignableFrom(action.java)
    }

}

