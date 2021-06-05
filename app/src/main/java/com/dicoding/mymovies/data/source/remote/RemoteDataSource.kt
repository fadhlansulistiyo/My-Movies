package com.dicoding.mymovies.data.source.remote

import android.util.Log
import com.dicoding.mymovies.data.source.remote.response.*
import com.dicoding.mymovies.network.ApiConfig
import com.dicoding.mymovies.utils.ConstantValue.API_KEY
import com.dicoding.mymovies.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        client.enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                callback.onMoviesLoaded(response.body()?.results)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Log.d("RemoteDataSource,", "getMovies onFailure: ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
    }

    fun getDetailMovies(callback: LoadDetailMoviesCallback, moviesId: String) {
        EspressoIdlingResource.increment()
        val client = ApiConfig.getApiService().getDetailMovies(moviesId, API_KEY)
        client.enqueue(object : Callback<DetailMoviesResponse> {
            override fun onResponse(
                call: Call<DetailMoviesResponse>,
                response: Response<DetailMoviesResponse>
            ) {
                callback.onDetailMoviesLoaded(response.body())
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<DetailMoviesResponse>, t: Throwable) {
                Log.d("RemoteDataSource,", "getDetailMovies onFailure: ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
    }

    fun getTvShow(callback: LoadTvShowCallback) {
        EspressoIdlingResource.increment()
        val client = ApiConfig.getApiService().getTvShow(API_KEY)
        client.enqueue(object : Callback<TVShowResponse> {
            override fun onResponse(
                call: Call<TVShowResponse>,
                response: Response<TVShowResponse>
            ) {
                callback.onTvShowLoaded(response.body()?.results)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TVShowResponse>, t: Throwable) {
                Log.d("RemoteDataSource,", "getTvShow onFailure: ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
    }

    fun getDetailTvShow(callback: LoadDetailTvShowCallback, tvShowId: String) {
        EspressoIdlingResource.increment()
        val client = ApiConfig.getApiService().getDetailTvShow(tvShowId, API_KEY)
        client.enqueue(object : Callback<DetailTvShowResponse> {
            override fun onResponse(
                call: Call<DetailTvShowResponse>,
                response: Response<DetailTvShowResponse>
            ) {
                callback.onDetailTvShowLoaded(response.body())
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<DetailTvShowResponse>, t: Throwable) {
                Log.d("RemoteDataSource,", "getDetailTvShow onFailure: ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
    }

    //-------------interface

    interface LoadMoviesCallback {
        fun onMoviesLoaded(movies : List<Movies>?)
    }

    interface LoadDetailMoviesCallback {
        fun onDetailMoviesLoaded(moviesDetail: DetailMoviesResponse?)
    }

    interface LoadTvShowCallback {
        fun onTvShowLoaded(tvShow: List<TvShow>?)
    }

    interface LoadDetailTvShowCallback {
        fun onDetailTvShowLoaded(tvShowDetail: DetailTvShowResponse?)
    }
}