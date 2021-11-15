package com.slin.score.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * author: slin
 * date: 2021/11/15
 * description:
 *
 */
abstract class MviViewModel<S : ViewState, A : Action>(initialState: S) : ViewModel() {

    private val nextAction: MutableSharedFlow<Pair<A, ActionResult>> = MutableSharedFlow()

    private val reducerRegister: ReducerRegister<S, A> by lazy {
        ReducerRegister<S, A>().also { onStart(it) }
    }

    private val _viewState: MutableStateFlow<S> = MutableStateFlow(initialState)

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

    protected abstract fun onStart(register: ReducerRegister<S, A>)

    fun dispatch(action: A, result: ActionResult) {
        viewModelScope.launch {
            nextAction.emit(Pair(action, result))
        }
    }
}





