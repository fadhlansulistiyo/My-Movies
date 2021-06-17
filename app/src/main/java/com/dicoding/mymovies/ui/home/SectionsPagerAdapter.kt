package com.dicoding.mymovies.ui.home

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dicoding.mymovies.R
import com.dicoding.mymovies.ui.movies.MoviesFragment
import com.dicoding.mymovies.ui.tvshow.TvShowFragment

class SectionsPagerAdapter(private val fragmentList: List<Fragment>, fm: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fm, lifecycle) {

    override fun getItemCount(): Int =
        fragmentList.size

    override fun createFragment(position: Int): Fragment =
        fragmentList[position]

}