package com.dicoding.mymovies.data.source.remote

class RemoteDataSource private constructor{

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance() : RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }

    fun getMovies(callback: LoadMoviesCallback) {
        val client = ApiConfig.increment()
    }
}