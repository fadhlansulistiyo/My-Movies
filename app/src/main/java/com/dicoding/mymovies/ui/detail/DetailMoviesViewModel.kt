package com.dicoding.mymovies.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.mymovies.data.source.MoviesRepository
import com.dicoding.mymovies.data.source.local.entity.DetailEntity
import com.dicoding.mymovies.data.source.local.entity.MoviesEntity
import com.dicoding.mymovies.data.source.local.entity.TvShowEntity
import com.dicoding.mymovies.utils.DataDummy
import com.dicoding.mymovies.vo.Resource

class DetailMoviesViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    companion object {
        const val MOVIES = "movies"
        const val TV_SHOW = "tvShow"
    }

    private lateinit var detailMovies: LiveData<Resource<MoviesEntity>>
    private lateinit var detailTvShow: LiveData<Resource<TvShowEntity>>
    // private lateinit var detailData : LiveData<DetailEntity>

    fun setMoviesTvShow(id: String, category: String) {
        when (category) {
            MOVIES -> {
                detailMovies = moviesRepository.getDetailMovies(id.toInt())
            }

            TV_SHOW -> {
                detailTvShow = moviesRepository.getDetailTvShow(id.toInt())
            }
        }
    }

    fun setFavoriteMovies() {
        val resource = detailMovies.value
        if (resource?.data != null) {
            val newState = !resource.data.favorite
            moviesRepository.setFavoriteMovies(resource.data, newState)
        }
    }

    fun getDetailMovies() = detailMovies

    fun setFavoriteTvShow() {
        val resource = detailTvShow.value
        if (resource?. data != null) {
            val newState = !resource.data.favorite
            moviesRepository.setFavoriteTvShow(resource.data, newState)
        }
    }

    fun getDetailTvShow() = detailTvShow
}