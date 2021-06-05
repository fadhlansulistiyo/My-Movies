package com.dicoding.mymovies.data.source

import androidx.lifecycle.LiveData
import com.dicoding.mymovies.data.source.local.entity.DetailEntity
import com.dicoding.mymovies.data.source.local.entity.MoviesEntity
import com.dicoding.mymovies.data.source.local.entity.TvShowEntity

interface MoviesDataSource {

    fun getMovies() : LiveData<List<MoviesEntity>>

    fun getDetailMovies(moviesId: String) : LiveData<DetailEntity>

    fun getTvShow() : LiveData<List<TvShowEntity>>

    fun getDetailTvShow(tvShowId: String) : LiveData<DetailEntity>
}