package com.slin.test.ui.mvi

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.slin.core.logger.logd
import com.slin.test.R
import com.slin.test.base.BaseFragment
import com.slin.test.databinding.FragmentTestMviBinding
import kotlinx.coroutines.flow.collect

/**
 * author: slin
 *
 * date: 2021/11/16
 *
 * description:
 *
 *
 */
class TestMviFragment : BaseFragment<FragmentTestMviBinding>(R.layout.fragment_test_mvi) {

    private val testMviViewModel: TestMviViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        logd { "onViewCreated" }
        lifecycleScope.launchWhenStarted {
            logd { "launchWhenStarted" }
            testMviViewModel.viewState.collect {
                logd { "collect" }
                binding.tvText.text = it.text
                binding.tvTime.text = it.time
            }
        }

        binding.tvText.setOnClickListener {
            logd { "tvText.setOnClickListener" }
            testMviViewModel.dispatch(TestAction.ClickTextAction("Hello MVI"))
        }

        binding.tvTime.setOnClickListener {
            logd { "tvTime.setOnClickListener" }
            testMviViewModel.dispatch(TestAction.TimerStateAction)
        }

    }

}