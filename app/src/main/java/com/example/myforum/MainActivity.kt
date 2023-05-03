package com.example.myforum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.example.myforum.adapter.AdapterHomeFragment
import com.example.myforum.fragment.HomeFragment
import com.example.myforum.fragment.CommunityFragment
import com.example.myforum.fragment.MineFragment

enum class FragmentTab(
    val defaultIcon: Int,
    val activeIcon: Int,
    val getFragment: () -> Fragment,
    val labelName: Int
    ) {

    HomeFragmentTab(R.drawable.home, R.drawable.books, {
        HomeFragment.newInstance("", "")
    }, R.string.main_activity_home_tab),

    CommunityFragmentTab(R.drawable.books, R.drawable.home, {
        CommunityFragment.newInstance("", "")
    }, R.string.main_activity_mobile_tab),

    MineFragmentTab(R.drawable.mine, R.drawable.books, {
        MineFragment.newInstance("", "")
    }, R.string.main_activity_mine_tab)
}

class MainActivity : AppCompatActivity() {
    private lateinit var vpComment: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewPager2()
        initBottomNavigationBar()
    }

    private fun initViewPager2() {
        vpComment = findViewById<ViewPager2>(R.id.vpComment).apply {
            // activity每创建一次,fragment就重新创建一次,否则生命周期不匹配(activity生命周期 > fragment)
            adapter = AdapterHomeFragment(this@MainActivity, FragmentTab.values().map {
                it.getFragment()
            })
        }
    }

    private fun initBottomNavigationBar() {
        findViewById<BottomNavigationBar>(R.id.bottom_navigation_bar).apply {
            FragmentTab.values().forEach {
                this.addItem(
                    BottomNavigationItem(
                        it.defaultIcon,
                        it.labelName
                    ).setInactiveIconResource(it.activeIcon)
                )
            }
            setFirstSelectedPosition(0)
                .initialise()
        }.setTabSelectedListener(object : BottomNavigationBar.OnTabSelectedListener {
            override fun onTabSelected(position: Int) {
                vpComment.currentItem = position
            }

            override fun onTabUnselected(position: Int) {
            }

            override fun onTabReselected(position: Int) {
            }

        })
    }
}