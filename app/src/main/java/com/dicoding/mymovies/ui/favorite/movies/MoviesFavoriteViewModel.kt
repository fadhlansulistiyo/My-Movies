package com.dicoding.mymovies.ui.favorite.movies

import androidx.lifecycle.ViewModel
import com.dicoding.mymovies.data.source.MoviesRepository
import com.dicoding.mymovies.data.source.local.entity.MoviesEntity

class MoviesFavoriteViewModel(private val repository: MoviesRepository) : ViewModel() {
    fun getFavoriteMovies() = repository.getFavoriteMovies()

    fun setFavoriteMovies(moviesEntity: MoviesEntity) {
        val newState = !moviesEntity.favorite
        repository.setFavoriteMovies(moviesEntity, newState)
    }
}