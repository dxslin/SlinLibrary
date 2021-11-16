package com.slin.score.mvi.page

import com.slin.score.mvi.MviViewModel
import com.slin.score.mvi.ReducerRegister

/**
 * author: slin
 *
 * date: 2021/11/15
 *
 * description: 分页加载的ViewModel
 *
 * 处理分页加载状态和逻辑
 *
 */
abstract class PageViewModel<T> : MviViewModel<PageViewState<T>, PageAction>(PageViewState()) {

    override fun onStart(register: ReducerRegister<PageViewState<T>, PageAction>) {
        register.addReducer(PageAction.Refresh::class) { state, _ ->
            emit(state.copy(true))
            try {
                val res = refresh()
                emit(state.copy(loading = false, page = 0, data = res, null))
            } catch (e: Exception) {
                emit(state.copy(loading = false, page = 0, data = emptyList(), exception = e))
            }
        }

        register.addReducer(PageAction.LoadMore::class) { state, _ ->
            emit(state.copy(true))
            val page = state.page.inc()
            try {
                val data = state.data + loadMore(page)
                emit(state.copy(false, page, data, null))
            } catch (e: Exception) {
                emit(state.copy(false, page, emptyList(), e))
            }
        }

    }

    abstract suspend fun loadMore(page: Int): List<T>

    abstract suspend fun refresh(): List<T>

}