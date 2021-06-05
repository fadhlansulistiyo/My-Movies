package com.dicoding.mymovies.data.source.local.entity

data class TvShowEntity(
    var tvShowId: Int,
    var title: String,
    var releaseDate: String,
    var genre: String,
    var rating: String,
    var episode: String,
    var overview: String,
    var image: Int
)