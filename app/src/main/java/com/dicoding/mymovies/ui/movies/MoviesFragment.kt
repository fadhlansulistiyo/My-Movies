package com.dicoding.mymovies.ui.movies

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.mymovies.databinding.FragmentMoviesBinding
import com.dicoding.mymovies.ui.detail.DetailMoviesActivity
import com.dicoding.mymovies.ui.detail.DetailMoviesViewModel.Companion.MOVIES
import com.dicoding.mymovies.viewmodel.ViewModelFactory

class MoviesFragment : Fragment(), MoviesAdapter.OnItemClickCallback {

    private lateinit var fragmentMoviesBinding: FragmentMoviesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentMoviesBinding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return fragmentMoviesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[MoviesViewModel::class.java]

            val moviesAdapter = MoviesAdapter()

            fragmentMoviesBinding.progressBar.visibility = View.VISIBLE
            viewModel.getMovies().observe(viewLifecycleOwner, { movies ->
                fragmentMoviesBinding.progressBar.visibility = View.GONE
                moviesAdapter.setMovies(movies)
                moviesAdapter.setOnItemClickCallback(this)
                moviesAdapter.notifyDataSetChanged()
            })

            with(fragmentMoviesBinding.rvMovies) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = moviesAdapter
            }
        }
    }

    override fun onItemClicked(id: String) {
        val intent = Intent(context, DetailMoviesActivity::class.java)
        intent.putExtra(DetailMoviesActivity.EXTRA_FILM, id)
        intent.putExtra(DetailMoviesActivity.EXTRA_CATEGORY, MOVIES)

        context?.startActivity(intent)
    }
}