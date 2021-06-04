package com.dicoding.mymovies.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TVShowResponse(
    var tvShowId: Int,
    var title: String,
    var releaseDate: String,
    var genre: String,
    var rating: String,
    var episode: String,
    var overview: String,
    var image: Int
) : Parcelable