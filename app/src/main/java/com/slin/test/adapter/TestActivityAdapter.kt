package com.slin.test.adapter

import android.app.Activity
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
        helper.setBackgroundRes(R.id.iv_test_img, item.imgRes)
        helper.setText(R.id.tv_test_name, item.name)
    }

}


class TestActivityBean(var clazz: Class<out Activity>, var name: String, @DrawableRes var imgRes: Int)