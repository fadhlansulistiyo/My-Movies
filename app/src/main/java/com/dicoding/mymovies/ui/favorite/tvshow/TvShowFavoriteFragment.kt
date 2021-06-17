package com.dicoding.mymovies.ui.favorite.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dicoding.mymovies.R
import com.dicoding.mymovies.databinding.FragmentTvShowBinding

class TvShowFavoriteFragment : Fragment(), TvShowFavoriteAdapter.OnItemClickCallback {

    private val fragmentTvShowBinding: FragmentTvShowBinding? = null
    private val binding get() = fragmentTvShowBinding

    private lateinit var adapter: TvShowFavoriteAdapter
    private lateinit var viewModel: TvShowFavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentTvShowBinding = FragmentTvShowBinding.inflate(layoutInflater, container, false)

    }

    override fun onItemClicked(id: String) {
        TODO("Not yet implemented")
    }

}