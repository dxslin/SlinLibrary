package com.slin.test.ui.svs

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.slin.core.net.status.SvsState
import com.slin.svs.DefaultStateView
import com.slin.svs.StateView
import com.slin.svs.StateViewSwitcher
import com.slin.test.R
import com.slin.test.base.BaseFragment
import com.slin.test.databinding.FragmentSvsTestBinding
import java.io.IOException

class SvsTestFragment : BaseFragment<FragmentSvsTestBinding>(R.layout.fragment_svs_test) {

    init {
        StateViewSwitcher.config(object : StateView.Factory {
            override fun create(): StateView {
                return TestStateView()
            }
        })
    }

    private lateinit var stateViewSwitcher: StateViewSwitcher

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.initView()

    }

    private fun FragmentSvsTestBinding.initView() {

        stateViewSwitcher = StateViewSwitcher(recyclerView)

        btnInitial.setOnClickListener { stateViewSwitcher.stateChange(SvsState.Initial) }
        btnLoading.setOnClickListener { stateViewSwitcher.stateChange(SvsState.Loading(false)) }
        btnLoadSuccess.setOnClickListener {
            stateViewSwitcher.stateChange(
                SvsState.LoadSuccess(true)
            )
        }
        btnLoadFailNoData.setOnClickListener {
            stateViewSwitcher.stateChange(
                SvsState.LoadSuccess(false)
            )
        }
        btnLoadFailNoNetwork.setOnClickListener {
            stateViewSwitcher.stateChange(
                SvsState.LoadFail(NoNetworkException())
            )
        }
        btnLoadFailError.setOnClickListener {
            stateViewSwitcher.stateChange(
                SvsState.LoadFail(IOException("IOError"))
            )
        }


    }


    companion object {
        @JvmStatic
        fun newInstance() = SvsTestFragment()
    }
}


class NoNetworkException : Exception()

class TestStateView : DefaultStateView() {

    private fun isNoNetwork(throwable: Throwable?): Boolean {
        return throwable is NoNetworkException
    }

    override fun obtainFailDrawable(context: Context, throwable: Throwable?): Drawable? {
        return if (isNoNetwork(throwable)) {
            ContextCompat.getDrawable(context, com.slin.svs.R.drawable.svs_ic_no_network)
        } else {
            ContextCompat.getDrawable(context, com.slin.svs.R.drawable.svs_ic_load_fail)
        }
    }

    override fun obtainFailText(context: Context, throwable: Throwable?): CharSequence {
        return if (isNoNetwork(throwable)) {
            context.getString(R.string.lk_svs_no_network)
        } else {
            context.getString(R.string.lk_svs_error)
        }
    }

}

