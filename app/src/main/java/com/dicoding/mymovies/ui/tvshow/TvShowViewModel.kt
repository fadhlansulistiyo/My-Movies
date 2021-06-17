package com.dicoding.mymovies.ui.tvshow

import androidx.lifecycle.ViewModel
import com.dicoding.mymovies.data.source.MoviesRepository
import com.dicoding.mymovies.data.source.local.entity.TvShowEntity
import com.dicoding.mymovies.utils.DataDummy

class TvShowViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    fun getTvShow(sort: String) = moviesRepository.getTvShow(sort)
}