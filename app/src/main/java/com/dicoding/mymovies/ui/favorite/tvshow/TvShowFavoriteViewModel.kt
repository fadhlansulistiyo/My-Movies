package com.dicoding.mymovies.ui.favorite.tvshow

import androidx.lifecycle.ViewModel
import com.dicoding.mymovies.data.source.MoviesRepository
import com.dicoding.mymovies.data.source.local.entity.TvShowEntity

class TvShowFavoriteViewModel(private val repository: MoviesRepository) : ViewModel() {
    fun getFavoriteTvShow() = repository.getFavoriteTvShow()

    fun setFavoriteTvShow(tvShowEntity: TvShowEntity) {
        val newState = !tvShowEntity.favorite
        repository.setFavoriteTvShow(tvShowEntity, newState)
    }
}