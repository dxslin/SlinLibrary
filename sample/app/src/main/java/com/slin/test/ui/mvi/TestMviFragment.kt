package com.slin.test.ui.mvi

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.slin.test.R
import com.slin.test.base.BaseFragment
import com.slin.test.databinding.FragmentTestMviBinding

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

        lifecycleScope.launchWhenStarted {
            testMviViewModel.viewState.collect {
                binding.tvText.text = it.text
                binding.tvTime.text = it.time
            }
        }

        binding.tvText.setOnClickListener {
            testMviViewModel.dispatch(TestAction.ClickTextAction("Hello MVI"))
        }

        binding.tvTime.setOnClickListener {
            testMviViewModel.dispatch(TestAction.TimerStateAction)
        }

    }

}