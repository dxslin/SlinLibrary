package com.slin.test.ui.mvi

import com.slin.score.mvi.*
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*

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

    class ClickTextAction(val text: String) : TestAction()

    object TimerStateAction : TestAction()

}

data class TestViewState(

    val text: String = "Click me",

    val time: String = "(*_*)",

    var timerStatus: Boolean = false,

    ) : ViewState


class TestMviViewModel : MviViewModel<TestViewState, TestAction>(TestViewState()) {


    override fun onStart(register: ReducerRegister<TestViewState, TestAction>) {
        register.addReducer(TestAction::class) { viewState, action ->
            when (action) {
                is TestAction.ClickTextAction -> {
                    val state = viewState.copy(text = action.text)
                    emit(state)
                }
                is TestAction.TimerStateAction -> {
                    val state = if (viewState.timerStatus) {
                        viewState.timerStatus = false
                        viewState.copy(time = "(*_*)")
                    } else {
                        viewState.timerStatus = true
                        startTimer()
                        viewState.copy(time = nowDate())
                    }
                    emit(state)
                }
                else -> {
                }
            }
        }
    }

    private suspend fun FlowCoroutineScope<TestViewState>.startTimer() {
        while (viewState.value.timerStatus) {
            emit(viewState.value.copy(time = nowDate()))
            delay(1000)
        }
    }

    private fun nowDate(): String {
        return SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss",
            Locale.getDefault()
        ).format(Date())
    }

}