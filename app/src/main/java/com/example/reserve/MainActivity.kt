package com.example.reserve

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.*
import java.io.IOException

private const val NUM_PAGES = 3

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.viewPager)
        viewPager.isUserInputEnabled = false
        viewPager.adapter = ViewPagerAdapter(this)

        tabLayout = findViewById(R.id.tabLayout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.setIcon(R.drawable.search)
                1 -> tab.setIcon(R.drawable.star_icon)
                2 -> tab.setIcon(R.drawable.calendar)
                3 -> tab.setIcon(R.drawable.profile)
            }
        }.attach()
    }

    override fun onResume() {
        super.onResume()
        viewPager.adapter = ViewPagerAdapter(this)

        tabLayout = findViewById(R.id.tabLayout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.setIcon(R.drawable.search)
                1 -> tab.setIcon(R.drawable.star_icon)
                2 -> tab.setIcon(R.drawable.calendar)
                3 -> tab.setIcon(R.drawable.profile)
            }
        }.attach()
    }


    private inner class ViewPagerAdapter(activity: MainActivity) : FragmentStateAdapter(activity) {
        override fun getItemCount(): Int {
            return NUM_PAGES
        }

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                1 -> FavoritesFragment.newInstance()
                2 -> CalendarFragment.newInstance()
                else -> HomeFragment.newInstance()
            }
        }

    }
}
