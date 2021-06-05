package com.dicoding.mymovies.data.source.local.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShowEntity(
    @SerializedName("id")
    var moviesId: Int,

    @SerializedName("title")
    var title: String,

    @SerializedName("poster_path")
    var image: String,

    @SerializedName("vote_average")
    var rating: Double,
) : Parcelable