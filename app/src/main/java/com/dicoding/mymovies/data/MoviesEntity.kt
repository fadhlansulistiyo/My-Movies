package com.dicoding.mymovies.data

data class MoviesEntity(
    var moviesId: Int,
    var title: String,
    var releaseDate: String,
    var genre: String,
    var rating: String,
    var duration: String,
    var overview: String,
    var image: Int
)