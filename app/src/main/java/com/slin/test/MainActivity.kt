package com.slin.test

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.slin.test.adapter.TestActivityAdapter
import com.slin.test.adapter.TestActivityBean

/**
 * author: slin
 * date: 2020-02-13
 * description:
 */
class MainActivity : AppCompatActivity() {

    private val testActivities = arrayListOf(
            TestActivityBean(DialogTestActivity::class.java, "SlinDialog", R.mipmap.img_timeout),
            TestActivityBean(IndicatorTestActivity::class.java, "ViewPagerIndicator", R.mipmap.img_empty)
    )

    private lateinit var adapter: TestActivityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvTestActivities = findViewById<RecyclerView>(R.id.rv_test_activities)
        adapter = TestActivityAdapter(testActivities)
        rvTestActivities.adapter = adapter
        rvTestActivities.layoutManager = GridLayoutManager(this, 2)
        adapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
            startActivity(Intent(this@MainActivity, this@MainActivity.adapter.getItem(position)?.clazz))
        }

    }


}