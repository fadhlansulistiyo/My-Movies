package com.dicoding.mymovies.ui.movies

import androidx.lifecycle.ViewModel
import com.dicoding.mymovies.data.source.local.entity.MoviesEntity
import com.dicoding.mymovies.utils.DataDummy

class MoviesViewModel : ViewModel() {

    fun getMovies() : List<MoviesEntity> = DataDummy.generateDummyMovies()
}