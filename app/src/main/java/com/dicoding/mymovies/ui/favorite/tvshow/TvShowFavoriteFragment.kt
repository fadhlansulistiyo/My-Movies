package com.dicoding.mymovies.ui.favorite.tvshow

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
import com.dicoding.mymovies.databinding.FragmentTvShowFavoriteBinding
import com.dicoding.mymovies.ui.detail.DetailMoviesActivity
import com.dicoding.mymovies.ui.detail.DetailMoviesViewModel.Companion.TV_SHOW
import com.dicoding.mymovies.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class TvShowFavoriteFragment : Fragment(), TvShowFavoriteAdapter.OnItemClickCallback {

    private var fragmentTvShowFavoriteBinding: FragmentTvShowFavoriteBinding? = null
    private val binding get() = fragmentTvShowFavoriteBinding

    private lateinit var adapter: TvShowFavoriteAdapter
    private lateinit var viewModel: TvShowFavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentTvShowFavoriteBinding = FragmentTvShowFavoriteBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemTouchHelper.attachToRecyclerView(binding?.rvFavoriteTvShow)

        if(activity != null) {
            val factory = ViewModelFactory.getInstance(requireContext())
            viewModel = ViewModelProvider(this, factory)[TvShowFavoriteViewModel::class.java]

            adapter = TvShowFavoriteAdapter()
            adapter.setOnItemClickCallback(this)

            viewModel.getFavoriteTvShow().observe(viewLifecycleOwner, { favoriteTvShow ->
                if (favoriteTvShow != null) {
                    adapter.submitList(favoriteTvShow)
                }
            })

            with(binding?.rvFavoriteTvShow) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = adapter
            }
        }
    }

    override fun onItemClicked(id: String) {
        val intent = Intent(context, DetailMoviesActivity::class.java)
        intent.putExtra(DetailMoviesActivity.EXTRA_FILM, id)
        intent.putExtra(DetailMoviesActivity.EXTRA_CATEGORY, TV_SHOW)
        context?.startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavoriteTvShow().observe(viewLifecycleOwner, { favoriteTvShow ->
            if (favoriteTvShow != null) {
                adapter.submitList(favoriteTvShow)
            }
        })
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
                val tvShowEntity = adapter.getSwipedData(swipedPosition)
                tvShowEntity?.let { viewModel.setFavoriteTvShow(it) }

                val snackBar = Snackbar.make(requireView(), R.string.undo, Snackbar.LENGTH_LONG)
                snackBar.setAction(R.string.ok) { _ ->
                    tvShowEntity?.let {
                        viewModel.setFavoriteTvShow(it)
                    }
                    snackBar.show()
                }
            }
        }
    })

}