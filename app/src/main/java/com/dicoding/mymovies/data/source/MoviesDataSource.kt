package com.dicoding.mymovies.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.dicoding.mymovies.data.source.local.entity.DetailEntity
import com.dicoding.mymovies.data.source.local.entity.MoviesEntity
import com.dicoding.mymovies.data.source.local.entity.TvShowEntity
import com.dicoding.mymovies.vo.Resource

interface MoviesDataSource {

    fun getMovies(sort: String) : LiveData<Resource<PagedList<MoviesEntity>>>

    fun getDetailMovies(moviesId: Int) : LiveData<Resource<MoviesEntity>>

    fun getFavoriteMovies() : LiveData<PagedList<MoviesEntity>>

    fun setFavoriteMovies(movies: MoviesEntity, state: Boolean)

    fun getTvShow(sort: String) : LiveData<Resource<PagedList<TvShowEntity>>>

    fun getDetailTvShow(tvShowId: Int) : LiveData<Resource<TvShowEntity>>

    fun getFavoriteTvShow() : LiveData<PagedList<TvShowEntity>>

    fun setFavoriteTvShow(tvShow: TvShowEntity, state: Boolean)
}