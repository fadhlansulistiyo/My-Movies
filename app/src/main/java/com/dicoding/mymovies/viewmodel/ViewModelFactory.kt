package com.dicoding.mymovies.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.mymovies.data.source.MoviesRepository
import com.dicoding.mymovies.di.Injection
import com.dicoding.mymovies.ui.detail.DetailMoviesViewModel
import com.dicoding.mymovies.ui.movies.MoviesViewModel
import com.dicoding.mymovies.ui.tvshow.TvShowViewModel

class ViewModelFactory private constructor(private val moviesRepository: MoviesRepository) : ViewModelProvider.NewInstanceFactory(){

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context) : ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }

    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MoviesViewModel::class.java) -> {
                MoviesViewModel(moviesRepository) as T
            }
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                TvShowViewModel(moviesRepository) as T
            }
            modelClass.isAssignableFrom(DetailMoviesViewModel::class.java) -> {
                DetailMoviesViewModel(moviesRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}