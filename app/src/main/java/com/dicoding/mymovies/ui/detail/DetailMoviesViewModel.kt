package com.dicoding.mymovies.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.mymovies.data.source.MoviesRepository
import com.dicoding.mymovies.data.source.local.entity.DetailEntity
import com.dicoding.mymovies.data.source.local.entity.MoviesEntity
import com.dicoding.mymovies.data.source.local.entity.TvShowEntity
import com.dicoding.mymovies.utils.DataDummy

class DetailMoviesViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    companion object {
        const val MOVIES = "movies"
        const val TV_SHOW = "tvShow"
    }

    private lateinit var detailData : LiveData<DetailEntity>

    fun setMoviesTvShow(id: String, categoty: String) {
        when (categoty) {
            MOVIES -> {
                detailData = moviesRepository.getDetailMovies(id)
            }

            TV_SHOW -> {
                detailData = moviesRepository.getDetailTvShow(id)
            }
        }
    }

    fun getDetailData() = detailData

    fun getMovies() : List<MoviesEntity> = DataDummy.generateDummyMovies()

    fun getTvShow() : List<TvShowEntity> = DataDummy.generateDummyTvshow()
}