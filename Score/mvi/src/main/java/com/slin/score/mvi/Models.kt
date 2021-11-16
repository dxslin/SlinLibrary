package com.slin.score.mvi

/**
 * author: slin
 * date: 2021/11/15
 * description:
 *
 */

/**
 * 界面状态
 *
 * 标记接口，实现了此接口的类均为对应界面的状态数据
 *
 */
interface ViewState

/**
 * 行为对象
 *
 * 标记接口，实现了此接口的类均为用户行为触发
 *
 */
interface Action

/**
 * 行为执行之后的回调
 */
typealias ActionResult = suspend () -> Unit

/**
 * 执行器（减速器）
 *
 * 对应Action的执行代码，可以通过[FlowCoroutineScope.emit]更改状态数据
 *
 */
typealias Reducer<S, A> = suspend FlowCoroutineScope<S>.(state: S, action: A) -> Unit


object EmptyViewState : ViewState

object EmptyAction : Action