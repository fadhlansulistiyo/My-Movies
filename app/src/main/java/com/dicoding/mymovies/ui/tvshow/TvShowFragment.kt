package com.dicoding.mymovies.ui.tvshow

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.mymovies.databinding.FragmentTvShowBinding
import com.dicoding.mymovies.ui.detail.DetailMoviesActivity
import com.dicoding.mymovies.ui.detail.DetailMoviesViewModel
import com.dicoding.mymovies.viewmodel.ViewModelFactory

class TvShowFragment : Fragment(), TvShowAdapter.OnItemClickCallback {

    private lateinit var fragmentTvShowBinding: FragmentTvShowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentTvShowBinding = FragmentTvShowBinding.inflate(inflater, container, false)
        return fragmentTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]

            val tvShowAdapter = TvShowAdapter()

            fragmentTvShowBinding.progressBar.visibility = View.VISIBLE
            viewModel.getTvShow().observe(viewLifecycleOwner, { tvShow ->
                fragmentTvShowBinding.progressBar.visibility = View.GONE
                tvShowAdapter.setTvShow(tvShow)
                tvShowAdapter.setOnItemClickCallback(this)
                tvShowAdapter.notifyDataSetChanged()
            })

            with(fragmentTvShowBinding.rvTvShow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
        }
    }

    override fun onItemClicked(id: String) {
        val intent = Intent(context, DetailMoviesActivity::class.java)
        intent.putExtra(DetailMoviesActivity.EXTRA_FILM, id)
        intent.putExtra(DetailMoviesActivity.EXTRA_CATEGORY, DetailMoviesViewModel.TV_SHOW)

        context?.startActivity(intent)
    }
}