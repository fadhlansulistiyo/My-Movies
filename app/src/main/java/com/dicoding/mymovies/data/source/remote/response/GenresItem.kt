package com.dicoding.mymovies.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GenresItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)
