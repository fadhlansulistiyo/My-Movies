package com.dicoding.mymovies.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.dicoding.mymovies.data.source.local.entity.MoviesEntity
import com.dicoding.mymovies.data.source.local.entity.TvShowEntity
import com.dicoding.mymovies.data.source.local.room.MoviesDao
import com.dicoding.mymovies.utils.SortUtils
import com.dicoding.mymovies.utils.SortUtils.MOVIES_ENTITIES
import com.dicoding.mymovies.utils.SortUtils.TV_SHOW_ENTITIES

class LocalDataSource(private val mMoviesDao: MoviesDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(moviesDao: MoviesDao) : LocalDataSource =
            INSTANCE ?: LocalDataSource(moviesDao)
    }

    fun getAllMovies(sort: String) : DataSource.Factory<Int, MoviesEntity> =
        mMoviesDao.getMovies(SortUtils.getSortedQuery(sort, MOVIES_ENTITIES))

    fun getMoviesById(id: Int) : LiveData<MoviesEntity> =
        mMoviesDao.getMoviesById(id)

    fun getFavoriteMovies() : DataSource.Factory<Int, MoviesEntity> =
        mMoviesDao.getFavoriteMovies()

    fun getAllTvShow(sort: String) : DataSource.Factory<Int, TvShowEntity> =
        mMoviesDao.getTvShow(SortUtils.getSortedQuery(sort, TV_SHOW_ENTITIES))

    fun getTvShowById(id: Int) : LiveData<TvShowEntity> =
        mMoviesDao.getTvShowById(id)

    fun getFavoriteTvShow() : DataSource.Factory<Int, TvShowEntity> =
        mMoviesDao.getFavoriteTvShow()

    fun insertMovies(movies: List<MoviesEntity>) =
        mMoviesDao.insertMovies(movies)

    fun setFavoriteMovies(movies: MoviesEntity, newState: Boolean) {
        movies.favorite = newState
        mMoviesDao.updateMovies(movies)
    }

    fun updateMovies(movies: MoviesEntity, newState: Boolean) {
        movies.favorite = newState
        mMoviesDao.updateMovies(movies)
    }

    fun insertTvShow(tvShow: List<TvShowEntity>) =
        mMoviesDao.insertTvShow(tvShow)

    fun setFavoriteTvShow(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.favorite = newState
        mMoviesDao.updateTvShow(tvShow)
    }

    fun updateTvShow(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.favorite = newState
        mMoviesDao.updateTvShow(tvShow)
    }
}