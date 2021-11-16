package com.slin.score.mvi

import android.app.Activity
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * author: slin
 *
 * date: 2021/11/15
 *
 * description: MVI模式的ViewModel
 *
 * 相比于MVVM，MVI统一了View与ViewModel交互接口，即通过[Action]来触发。ViewModel需要添加与[Action]对应的执行器[Reducer]。
 *
 * 用户行为[Action] -> 执行器[Reducer] -> 更改界面状态[ViewState] -> 绘制界面[View]
 *
 *  [MviViewModel.dispatch] --[Action]-> 执行[Reducer]，更改状态[FlowCoroutineScope.emit] --[ViewState]-> [Activity]/[Fragment]
 *
 */
abstract class MviViewModel<S : ViewState, A : Action>(initialState: S) : ViewModel() {

    private val nextAction: MutableSharedFlow<Pair<A, ActionResult>> = MutableSharedFlow()

    private val reducerRegister: ReducerRegister<S, A> by lazy {
        ReducerRegister<S, A>().also { onStart(it) }
    }

    private val _viewState: MutableStateFlow<S> = MutableStateFlow(initialState)

    /**
     * 界面状态
     */
    val viewState: StateFlow<S> = _viewState.asStateFlow()

    init {
        viewModelScope.launch {
            nextAction.collect {
                val reducer: Reducer<S, A> = reducerRegister[it.first::class]
                reducer.invoke(FlowCoroutineScopeImpl(_viewState, this), viewState.value, it.first)
                it.second.invoke()
            }
        }
    }

    /**
     * 在[register]中配置[Action]对应的执行器
     */
    protected abstract fun onStart(register: ReducerRegister<S, A>)

    /**
     * 提交并执行[Action]
     */
    fun dispatch(action: A, result: ActionResult) {
        viewModelScope.launch {
            nextAction.emit(Pair(action, result))
        }
    }
}





