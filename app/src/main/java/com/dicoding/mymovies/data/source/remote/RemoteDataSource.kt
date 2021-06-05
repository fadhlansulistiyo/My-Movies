package com.dicoding.mymovies.data.source.remote

import com.dicoding.mymovies.network.ApiConfig
import com.dicoding.mymovies.utils.ConstantValue.API_KEY
import com.dicoding.mymovies.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback

class RemoteDataSource{

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance() : RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }

    fun getMovies(callback: LoadMoviesCallback) {
        EspressoIdlingResource.increment()
        val client = ApiConfig.getApiService().getMovies(API_KEY)
        client.enqueue(object : Callback<>)
    }

    interface LoadMoviesCallback {
        fun onMoviesLoaded(movies : List<Movies>)
    }
}