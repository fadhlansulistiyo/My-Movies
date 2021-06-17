package com.dicoding.mymovies.ui.favorite.movies

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.mymovies.R
import com.dicoding.mymovies.databinding.FragmentMoviesFavoriteBinding
import com.dicoding.mymovies.ui.detail.DetailMoviesActivity
import com.dicoding.mymovies.ui.detail.DetailMoviesViewModel.Companion.MOVIES
import com.dicoding.mymovies.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class MoviesFavoriteFragment : Fragment(), MoviesFavoriteAdapter.OnItemClickCallback {

    private var fragmentMoviesFavoriteBinding: FragmentMoviesFavoriteBinding? = null
    private val binding get() = fragmentMoviesFavoriteBinding

    private lateinit var viewModel: MoviesFavoriteViewModel
    private lateinit var adapter: MoviesFavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentMoviesFavoriteBinding = FragmentMoviesFavoriteBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(binding?.rvFavoriteMovies)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[MoviesFavoriteViewModel::class.java]

            adapter = MoviesFavoriteAdapter()
            adapter.setOnItemClickCallback(this)

            viewModel.getFavoriteMovies().observe(viewLifecycleOwner, { favoriteMovies ->
                if (favoriteMovies != null) {
                    adapter.submitList(favoriteMovies)
                }
            })

            with(binding?.rvFavoriteMovies) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = adapter
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavoriteMovies().observe(viewLifecycleOwner, { favoriteMovies ->
            if (favoriteMovies != null) {
                adapter.submitList(favoriteMovies)
            }
        })
    }

    override fun onItemClicked(id: String) {
        val intent = Intent(context, DetailMoviesActivity::class.java)
        intent.putExtra(DetailMoviesActivity.EXTRA_FILM, id)
        intent.putExtra(DetailMoviesActivity.EXTRA_CATEGORY, MOVIES)
        context?.startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentMoviesFavoriteBinding = null
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val moviesEntity = adapter.getSwipedData(swipedPosition)
                moviesEntity?.let { viewModel.setFavoriteMovies(it) }

                val snackBar = Snackbar.make(requireView(), R.string.undo, Snackbar.LENGTH_LONG)
                snackBar.setAction(R.string.ok) { _ ->
                    moviesEntity?.let {
                        viewModel.setFavoriteMovies(it)
                    }
                    snackBar.show()
                }
            }
        }
    })
}