package com.dicoding.mymovies.network

import com.dicoding.mymovies.data.source.remote.response.DetailMoviesResponse
import com.dicoding.mymovies.data.source.remote.response.DetailTvShowResponse
import com.dicoding.mymovies.data.source.remote.response.MoviesResponse
import com.dicoding.mymovies.data.source.remote.response.TVShowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("discover/movie")
    fun getMovies(
        @Query("api_key") apiKey: String
    ) : Call<MoviesResponse>

    @GET("movie/{id}")
    fun getDetailMovies(
        @Path("id") id: String,
        @Query("api_key") apiKey: String
    ) : Call<DetailMoviesResponse>

    @GET("discover/tv")
    fun getTvShow(
        @Query("api_key") apiKey: String
    ) : Call<TVShowResponse>

    @GET("tv/{id}")
    fun getDetailTvShow(
        @Path("id") id: String,
        @Query("api_key") apiKey: String
    ) : Call<DetailTvShowResponse>

}