package com.slin.test.ui

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.slin.test.R
import com.slin.test.adapter.TestActivityAdapter
import com.slin.test.adapter.TestActivityBean
import com.slin.test.base.BaseActivity
import com.slin.test.databinding.ActivityMainBinding
import com.slin.test.ui.svs.SvsTestActivity

/**
 * author: slin
 * date: 2020-02-13
 * description:
 */
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val testActivities = arrayListOf(
            TestActivityBean(DialogTestActivity::class.java, "SlinDialog", R.mipmap.img_timeout),
            TestActivityBean(IndicatorTestActivity::class.java, "ViewPagerIndicator", R.mipmap.img_empty),
            TestActivityBean(SvsTestActivity::class.java, "SateViewSwitcher", R.mipmap.img_cartoon_1),
    )

    private lateinit var adapter: TestActivityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val rvTestActivities = binding.rvTestActivities
        adapter = TestActivityAdapter(testActivities)
        rvTestActivities.adapter = adapter
        rvTestActivities.layoutManager = GridLayoutManager(this, 2)
        adapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
            startActivity(Intent(this@MainActivity, this@MainActivity.adapter.getItem(position)?.clazz))
        }

    }


}