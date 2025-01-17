package com.dicoding.mymovies.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvShow(

    @SerializedName("backdrop_path")
    val backdropPath: String,

    @SerializedName("first_air_date")
    val firstAirDate: String,

    @SerializedName("genre_ids")
    val genreIds: List<Int>,

    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("original_language")
    val originalLanguage: String,

    @SerializedName("original_name")
    val originalName: String,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("vote_average")
    val voteAverage: Double,

)