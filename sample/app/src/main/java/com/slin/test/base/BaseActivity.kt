package com.slin.test.base

import androidx.viewbinding.ViewBinding
import com.slin.study.android.ext.ui.CoreActivity
import com.slin.viewbinding.ActivityBindingDelegate


/**
 * author: slin
 * date: 2021/1/26
 * description:
 *
 */
open class BaseActivity<VB : ViewBinding> : com.slin.study.android.ext.ui.CoreActivity() {

    protected val binding by ActivityBindingDelegate<VB>()


}
