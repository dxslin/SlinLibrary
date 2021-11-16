package com.slin.score.mvi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.FlowCollector

/**
 * author: slin
 * date: 2021/11/15
 * description: FlowCoroutineScope
 *
 * [FlowCollector]和[CoroutineScope]的代理Scope，可以在协程中获取数据之后调用[FlowCollector.emit]方法提交
 *
 */
interface FlowCoroutineScope<S : ViewState> : FlowCollector<S>, CoroutineScope

internal class FlowCoroutineScopeImpl<S : ViewState>(
    private val flowCollector: FlowCollector<S>,
    private val coroutineScope: CoroutineScope
) : FlowCoroutineScope<S>,
    FlowCollector<S> by flowCollector,
    CoroutineScope by coroutineScope