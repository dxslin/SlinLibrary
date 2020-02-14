package com.slin.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.slin.indicator.ViewPagerIndicator
import java.util.*

class IndicatorTestActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_indicator_test)

        val viewPagerIndicator = findViewById<ViewPagerIndicator>(R.id.viewPagerIndicator)
        val viewPager = findViewById<ViewPager>(R.id.viewPager)

        val title = listOf("销售", "php", "汽车电子开发工程师", "Android", "汽车项目管理", "厂长", "建筑设计师", "服务员")
        //设置tab标题
        viewPagerIndicator.setTabTitles(title)
        //绑定ViewPager
        viewPagerIndicator.bindViewPager(viewPager)

        val fragments: MutableList<Fragment> = ArrayList(title.size)
        for (element in title) {
            fragments.add(BlankFragment.newInstance(element))
        }

        viewPager.adapter = object : FragmentStatePagerAdapter(supportFragmentManager,
                BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            override fun getItem(position: Int): Fragment {
                return fragments[position]
            }

            override fun getCount(): Int {
                return fragments.size
            }
        }

    }
}
