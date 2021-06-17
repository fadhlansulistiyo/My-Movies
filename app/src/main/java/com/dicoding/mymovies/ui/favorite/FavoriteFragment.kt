package com.dicoding.mymovies.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dicoding.mymovies.R
import com.dicoding.mymovies.databinding.FragmentFavoriteBinding
import com.dicoding.mymovies.ui.favorite.movies.MoviesFavoriteFragment
import com.dicoding.mymovies.ui.favorite.tvshow.TvShowFavoriteFragment
import com.dicoding.mymovies.ui.home.HomeActivity
import com.dicoding.mymovies.ui.home.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class FavoriteFragment : Fragment() {

    private var favoriteFragmentBinding: FragmentFavoriteBinding? = null
    private val binding get() = favoriteFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        favoriteFragmentBinding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            (activity as HomeActivity).setActionBar("Favorite")

            val fragmentList = listOf(MoviesFavoriteFragment(), TvShowFavoriteFragment())
            val tabTitle = listOf(resources.getString(R.string.movies), resources.getString(R.string.tv_show))

            binding?.viewPagerFavorite?.adapter = SectionsPagerAdapter(fragmentList, requireActivity().supportFragmentManager, lifecycle)

            binding?.tabLayoutFavorite?.let {
                binding?.viewPagerFavorite?.let { it1 ->
                    TabLayoutMediator(it, it1) { tab, position ->
                        tab.text = tabTitle[position]
                    }.attach()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        favoriteFragmentBinding = null
    }

}