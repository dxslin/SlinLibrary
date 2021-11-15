package com.slin.score.mvi

/**
 * author: slin
 * date: 2021/11/15
 * description:
 *
 */

typealias ActionResult = suspend () -> Unit

interface ViewState

interface Action

typealias Reducer<S, A> = suspend FlowCoroutineScope<S>.(state: S, action: A) -> Unit


object EmptyViewState : ViewState

object EmptyAction : Action