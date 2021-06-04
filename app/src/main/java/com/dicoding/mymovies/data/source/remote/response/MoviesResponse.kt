package com.dicoding.mymovies.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MoviesResponse(
    var moviesId: Int,
    var title: String,
    var releaseDate: String,
    var genre: String,
    var rating: String,
    var duration: String,
    var overview: String,
    var image: Int
) : Parcelable