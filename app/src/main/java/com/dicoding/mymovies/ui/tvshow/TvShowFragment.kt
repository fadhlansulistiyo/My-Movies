package com.dicoding.mymovies.ui.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.mymovies.R
import com.dicoding.mymovies.data.source.local.entity.TvShowEntity
import com.dicoding.mymovies.databinding.FragmentTvShowBinding
import com.dicoding.mymovies.ui.detail.DetailMoviesActivity
import com.dicoding.mymovies.ui.detail.DetailMoviesViewModel
import com.dicoding.mymovies.viewmodel.ViewModelFactory
import com.dicoding.mymovies.utils.SortUtils.RANDOM
import com.dicoding.mymovies.utils.SortUtils.RATING_BEST
import com.dicoding.mymovies.utils.SortUtils.RATING_WORST
import com.dicoding.mymovies.vo.Resource
import com.dicoding.mymovies.vo.Status

class TvShowFragment : Fragment(), TvShowAdapter.OnItemClickCallback {

    private lateinit var fragmentTvShowBinding: FragmentTvShowBinding
    private lateinit var viewModel: TvShowViewModel
    private lateinit var tvShowAdapter: TvShowAdapter

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

            fragmentTvShowBinding.progressBar.visibility = View.VISIBLE

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]

            tvShowAdapter = TvShowAdapter()
            viewModel.getTvShow(RATING_BEST).observe(viewLifecycleOwner, tvShowObserver)

            with(fragmentTvShowBinding.rvTvShow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = tvShowAdapter
            }
        }
    }

    override fun onItemClicked(id: String) {
        val intent = Intent(context, DetailMoviesActivity::class.java)
        intent.putExtra(DetailMoviesActivity.EXTRA_FILM, id)
        intent.putExtra(DetailMoviesActivity.EXTRA_CATEGORY, DetailMoviesViewModel.TV_SHOW)
        context?.startActivity(intent)
    }

    private val tvShowObserver = Observer<Resource<PagedList<TvShowEntity>>> { tvShow ->
        if (tvShow != null) {
            when (tvShow.status) {
                Status.LOADING -> fragmentTvShowBinding.progressBar.visibility = View.VISIBLE
                Status.SUCCESS -> {
                    fragmentTvShowBinding.progressBar.visibility = View.INVISIBLE
                    tvShowAdapter.submitList(tvShow.data)
                    tvShowAdapter.setOnItemClickCallback(this)
                    tvShowAdapter.notifyDataSetChanged()
                }
                Status.ERROR -> {
                    fragmentTvShowBinding.progressBar.visibility = View.INVISIBLE
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

        viewModel.getTvShow(sort).observe(viewLifecycleOwner, tvShowObserver)
        item.isChecked = true

        return super.onOptionsItemSelected(item)
    }
}