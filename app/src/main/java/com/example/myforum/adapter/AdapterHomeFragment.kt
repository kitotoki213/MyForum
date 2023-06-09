package com.example.myforum.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myforum.FragmentTab

// ViewPager adapter
class AdapterHomeFragment(fragmentActivity: FragmentActivity, private val fragmentList: List<Fragment>): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment = fragmentList[position]
}