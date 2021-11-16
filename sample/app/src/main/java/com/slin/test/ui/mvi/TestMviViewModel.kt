package com.slin.test.ui.mvi

import com.slin.score.mvi.Action
import com.slin.score.mvi.MviViewModel
import com.slin.score.mvi.ReducerRegister
import com.slin.score.mvi.ViewState

/**
 * author: slin
 *
 * date: 2021/11/16
 *
 * description:
 *
 *
 */
sealed class TestAction : Action {

    class ClickTextAction(text: String) : TestAction()


}

class TestViewState(

    val text: String = "",


    ) : ViewState


class TestMviViewModel : MviViewModel<TestViewState, TestAction>(TestViewState()) {


    override fun onStart(register: ReducerRegister<TestViewState, TestAction>) {


    }


}