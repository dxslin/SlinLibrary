package com.slin.test.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import com.slin.test.base.BaseActivity
import com.slin.test.databinding.ActivityIndicatorTestBinding
import java.util.*

class IndicatorTestActivity : BaseActivity<ActivityIndicatorTestBinding>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val title = listOf("销售", "php", "汽车电子开发工程师", "Android", "汽车项目管理", "厂长", "建筑设计师", "服务员")
        //设置tab标题
        binding.viewPagerIndicator.setTabTitles(title)
        //绑定ViewPager
        binding.viewPagerIndicator.bindViewPager(binding.viewPager)

        val fragments: MutableList<Fragment> = ArrayList(title.size)
        for (element in title) {
            fragments.add(BlankFragment.newInstance(element))
        }

        binding.viewPager.adapter = object : FragmentStatePagerAdapter(supportFragmentManager,
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
