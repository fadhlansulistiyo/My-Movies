package com.dicoding.mymovies.ui.detail

import androidx.lifecycle.ViewModel
import com.dicoding.mymovies.data.MoviesEntity
import com.dicoding.mymovies.data.TvShowEntity
import com.dicoding.mymovies.utils.DataDummy

class DetailMoviesViewModel : ViewModel() {

    fun getDetailMovies(moviesId: Int, listMovies: ArrayList<MoviesEntity>) : MoviesEntity =
        DataDummy.getDetailMovies(moviesId, listMovies)

    fun getDetailTvShow(tvShowId: Int, listTvShow: ArrayList<TvShowEntity>) : TvShowEntity =
        DataDummy.getDetailTvShow(tvShowId, listTvShow)

    fun getMovies() : List<MoviesEntity> = DataDummy.generateDummyMovies()

    fun getTvShow() : List<TvShowEntity> = DataDummy.generateDummyTvshow()
}