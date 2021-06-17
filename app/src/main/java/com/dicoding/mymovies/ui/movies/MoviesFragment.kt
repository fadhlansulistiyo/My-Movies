package com.dicoding.mymovies.ui.movies

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.mymovies.R
import com.dicoding.mymovies.data.source.local.entity.MoviesEntity
import com.dicoding.mymovies.databinding.FragmentMoviesBinding
import com.dicoding.mymovies.ui.detail.DetailMoviesActivity
import com.dicoding.mymovies.ui.detail.DetailMoviesViewModel.Companion.MOVIES
import com.dicoding.mymovies.utils.SortUtils.RANDOM
import com.dicoding.mymovies.utils.SortUtils.RATING_BEST
import com.dicoding.mymovies.utils.SortUtils.RATING_WORST
import com.dicoding.mymovies.viewmodel.ViewModelFactory
import com.dicoding.mymovies.vo.Resource
import com.dicoding.mymovies.vo.Status

class MoviesFragment : Fragment(), MoviesAdapter.OnItemClickCallback {

    private lateinit var fragmentMoviesBinding: FragmentMoviesBinding
    private lateinit var viewModel: MoviesViewModel
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentMoviesBinding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return fragmentMoviesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            fragmentMoviesBinding.progressBar.visibility = View.VISIBLE

            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[MoviesViewModel::class.java]

            moviesAdapter = MoviesAdapter()
            viewModel.getMovies(RATING_BEST).observe(viewLifecycleOwner, moviesObserver)

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

    private val moviesObserver = Observer<Resource<PagedList<MoviesEntity>>> { movies ->
        if (movies != null) {
            when (movies.status) {
                Status.LOADING -> fragmentMoviesBinding.progressBar.visibility = View.VISIBLE
                Status.SUCCESS -> {
                    fragmentMoviesBinding.progressBar.visibility = View.INVISIBLE
                    moviesAdapter.submitList(movies.data)
                    moviesAdapter.setOnItemClickCallback(this)
                    moviesAdapter.notifyDataSetChanged()
                }
                Status.ERROR -> {
                    fragmentMoviesBinding.progressBar.visibility = View.INVISIBLE
                    Toast.makeText(context, "Error to load Movies", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        activity?.menuInflater?.inflate(R.menu.menu_sort, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var sort = ""
        when (item.itemId) {
            R.id.best_rating -> sort = RATING_BEST
            R.id.worst_rating -> sort = RATING_WORST
            R.id.random -> sort = RANDOM
        }

        viewModel.getMovies(sort).observe(viewLifecycleOwner, moviesObserver)
        item.isChecked = true

        return super.onOptionsItemSelected(item)
    }
}