package com.slin.test.adapter

import android.widget.TextView
import androidx.annotation.DrawableRes
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.slin.test.R

/**
 * author: slin
 * date: 2020-02-13
 * description:
 */
class TestActivityAdapter(data: List<TestActivityBean>) :
        BaseQuickAdapter<TestActivityBean, BaseViewHolder>(R.layout.item_test_activity, data) {


    override fun convert(helper: BaseViewHolder, item: TestActivityBean) {
        helper.getView<TextView>(R.id.tv_test_name).apply {
            text = item.name
            setCompoundDrawablesRelativeWithIntrinsicBounds(0, item.imgRes, 0, 0)
        }
    }

}


class TestActivityBean(var clazz: Class<out Any>, var name: String, @DrawableRes var imgRes: Int)