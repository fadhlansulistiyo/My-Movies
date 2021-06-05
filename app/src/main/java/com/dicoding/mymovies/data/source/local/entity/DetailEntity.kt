package com.dicoding.mymovies.data.source.local.entity

import com.google.gson.annotations.SerializedName

data class DetailEntity(

    @SerializedName("backdrop_path")
    val backdropPath: String,

    @SerializedName("genres")
    val genres: List<String>,

    @SerializedName("id")
    val id: Int,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("release_date")
    val releaseDate: String,

    @SerializedName("runtime")
    val runtime: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("vote_average")
    val voteAverage: Double
    
)