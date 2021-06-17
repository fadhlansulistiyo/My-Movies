package com.dicoding.mymovies.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    fun getMovies() : LiveData<ApiResponse<List<Movies>>> {
        EspressoIdlingResource.increment()
        val resultMovies = MutableLiveData<ApiResponse<List<Movies>>>()
        val client = ApiConfig.getApiService().getMovies(API_KEY)

        client.enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                resultMovies.value = ApiResponse.success(response.body()?.results as List<Movies>)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Log.d("RemoteDataSource,", "getMovies onFailure: ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
        return resultMovies
    }

    fun getDetailMovies(moviesId: String) : LiveData<ApiResponse<DetailMoviesResponse>> {
        EspressoIdlingResource.increment()
        val resultDetailMovies = MutableLiveData<ApiResponse<DetailMoviesResponse>>()
        val client = ApiConfig.getApiService().getDetailMovies(moviesId, API_KEY)

        client.enqueue(object : Callback<DetailMoviesResponse> {
            override fun onResponse(
                call: Call<DetailMoviesResponse>,
                response: Response<DetailMoviesResponse>
            ) {
                resultDetailMovies.value = ApiResponse.success(response.body() as DetailMoviesResponse)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<DetailMoviesResponse>, t: Throwable) {
                Log.d("RemoteDataSource,", "getDetailMovies onFailure: ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
        return resultDetailMovies
    }

    fun getTvShow() : LiveData<ApiResponse<List<TvShow>>> {
        EspressoIdlingResource.increment()
        val resultTvShow = MutableLiveData<ApiResponse<List<TvShow>>>()
        val client = ApiConfig.getApiService().getTvShow(API_KEY)

        client.enqueue(object : Callback<TVShowResponse> {
            override fun onResponse(
                call: Call<TVShowResponse>,
                response: Response<TVShowResponse>
            ) {
                resultTvShow.value = ApiResponse.success(response.body()?.results as List<TvShow>)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TVShowResponse>, t: Throwable) {
                Log.d("RemoteDataSource,", "getTvShow onFailure: ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
        return resultTvShow
    }

    fun getDetailTvShow(tvShowId: String) : LiveData<ApiResponse<DetailTvShowResponse>> {
        EspressoIdlingResource.increment()
        val resultDetailTvShow = MutableLiveData<ApiResponse<DetailTvShowResponse>>()
        val client = ApiConfig.getApiService().getDetailTvShow(tvShowId, API_KEY)

        client.enqueue(object : Callback<DetailTvShowResponse> {
            override fun onResponse(
                call: Call<DetailTvShowResponse>,
                response: Response<DetailTvShowResponse>
            ) {
                resultDetailTvShow.value = ApiResponse.success(response.body() as DetailTvShowResponse)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<DetailTvShowResponse>, t: Throwable) {
                Log.d("RemoteDataSource,", "getDetailTvShow onFailure: ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
        return resultDetailTvShow
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