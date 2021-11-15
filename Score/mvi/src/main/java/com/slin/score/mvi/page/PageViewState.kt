package com.slin.score.mvi.page

import com.slin.score.mvi.ViewState

/**
 * author: slin
 * date: 2021/11/15
 * description: 分页加载数据
 *
 */
data class PageViewState<T>(
    val loading: Boolean = false,
    val page: Int = 0,
    val data: List<T> = emptyList(),
    val exception: Exception? = null,
) : ViewState {

    /**
     * 是否有错误
     */
    val hasError: Boolean = exception != null


}