package com.slin.test.base

import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding
import com.slin.study.android.ext.ui.CoreFragment
import com.slin.viewbinding.FragmentBindingDelegate


/**
 * author: slin
 * date: 2021/1/27
 * description:
 *
 */
open class BaseFragment<VB : ViewBinding>(@LayoutRes layoutId: Int) :
    com.slin.study.android.ext.ui.CoreFragment(layoutId) {

    protected val binding: VB by FragmentBindingDelegate()


}