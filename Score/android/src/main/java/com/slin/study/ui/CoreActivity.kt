package com.slin.study.ui

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity


/**
 * author: slin
 * date: 2020/8/28
 * description: BaseActivity
 *
 */
open class CoreActivity : AppCompatActivity {

    constructor() : super()

    constructor(@LayoutRes layoutId: Int) : super(layoutId)


}
