package com.dicoding.mymovies.di

import android.content.Context
import com.dicoding.mymovies.data.source.MoviesRepository
import com.dicoding.mymovies.data.source.local.LocalDataSource
import com.dicoding.mymovies.data.source.local.room.MoviesDatabase
import com.dicoding.mymovies.data.source.remote.RemoteDataSource
import com.dicoding.mymovies.utils.AppExecutors

object Injection {

    fun provideRepository (context: Context) : MoviesRepository {
        val database = MoviesDatabase.getInstance(context
        )
        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.moviesDao())
        val appExecutors = AppExecutors()

        return MoviesRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}