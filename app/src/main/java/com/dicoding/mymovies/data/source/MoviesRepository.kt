package com.dicoding.mymovies.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.mymovies.data.source.local.entity.DetailEntity
import com.dicoding.mymovies.data.source.local.entity.MoviesEntity
import com.dicoding.mymovies.data.source.local.entity.TvShowEntity
import com.dicoding.mymovies.data.source.remote.RemoteDataSource
import com.dicoding.mymovies.data.source.remote.response.DetailMoviesResponse
import com.dicoding.mymovies.data.source.remote.response.DetailTvShowResponse
import com.dicoding.mymovies.data.source.remote.response.Movies
import com.dicoding.mymovies.data.source.remote.response.TvShow

class MoviesRepository private constructor(private val remoteDataSource: RemoteDataSource) : MoviesDataSource {

    companion object {
        @Volatile
        private var instance: MoviesRepository? = null

        fun getInstance(remoteData: RemoteDataSource) : MoviesRepository =
            instance ?: synchronized(this) {
                instance ?: MoviesRepository(remoteData)
            }
    }

    override fun getMovies(): LiveData<List<MoviesEntity>> {
        val moviesResult = MutableLiveData<List<MoviesEntity>>()

        remoteDataSource.getMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onMoviesLoaded(movies: List<Movies>?) {
                val moviesList = ArrayList<MoviesEntity>()
                if (movies != null) {
                    for (response in movies) {
                        with(response) {
                            val movie = MoviesEntity(id, title, posterPath, voteAverage)
                            moviesList.add(movie)
                        }
                    }
                    moviesResult.postValue(moviesList)
                }
            }
        })

        return moviesResult
    }

    override fun getDetailMovies(moviesId: String): LiveData<DetailEntity> {
        val moviesDetailResult = MutableLiveData<DetailEntity>()

        remoteDataSource.getDetailMovies(object : RemoteDataSource.LoadDetailMoviesCallback {
            override fun onDetailMoviesLoaded(moviesDetail: DetailMoviesResponse?) {
                if(moviesDetail != null) {
                    with(moviesDetail) {
                        val listGenres = ArrayList<String>()

                        for (genre in genres) {
                            listGenres.add(genre.name)
                        }

                        val detailMovies = DetailEntity(
                            title = title,
                            id = id,
                            backdropPath = backdropPath,
                            genres = listGenres,
                            overview = overview,
                            posterPath = posterPath,
                            releaseDate = releaseDate,
                            runtime = runtime,
                            voteAverage = voteAverage
                        )
                        moviesDetailResult.postValue(detailMovies)
                    }
                }
            }
        }, moviesId)
        return moviesDetailResult
    }

    override fun getTvShow(): LiveData<List<TvShowEntity>> {
        val tvShowResult = MutableLiveData<List<TvShowEntity>>()

        remoteDataSource.getTvShow(object : RemoteDataSource.LoadTvShowCallback {
            override fun onTvShowLoaded(tvShow: List<TvShow>?) {
                val tvShowList = ArrayList<TvShowEntity>()
                if (tvShow != null) {
                    for (response in tvShow) {
                        with(response) {
                            val tvShows = TvShowEntity(
                                id,
                                name,
                                posterPath,
                                voteAverage
                            )
                            tvShowList.add(tvShows)
                        }
                    }
                    tvShowResult.postValue(tvShowList)
                }
            }
        })
        return tvShowResult
    }

    override fun getDetailTvShow(tvShowId: String): LiveData<DetailEntity> {
        val tvShowDetailResult = MutableLiveData<DetailEntity>()

        remoteDataSource.getDetailTvShow(object : RemoteDataSource.LoadDetailTvShowCallback {
            override fun onDetailTvShowLoaded(tvShowDetail: DetailTvShowResponse?) {
                if (tvShowDetail != null) {
                    with(tvShowDetail) {
                        val listGenres = ArrayList<String>()

                        for (genre in genres) {
                            listGenres.add(genre.name)
                        }

                        val detailTvShow = DetailEntity(
                            title = name,
                            id = id,
                            backdropPath = backdropPath,
                            genres = listGenres,
                            overview = overview,
                            posterPath = posterPath,
                            releaseDate = firstAirDate,
                            runtime = episodeRunTime.average().toInt(),
                            voteAverage = voteAverage
                        )
                        tvShowDetailResult.postValue(detailTvShow)
                    }
                }
            }
        }, tvShowId)
        return tvShowDetailResult
    }

}