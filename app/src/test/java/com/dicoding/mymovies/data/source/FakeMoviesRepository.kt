package com.dicoding.mymovies.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dicoding.mymovies.data.NetworkBoundResource
import com.dicoding.mymovies.data.source.local.LocalDataSource
import com.dicoding.mymovies.data.source.local.entity.MoviesEntity
import com.dicoding.mymovies.data.source.local.entity.TvShowEntity
import com.dicoding.mymovies.data.source.remote.ApiResponse
import com.dicoding.mymovies.data.source.remote.RemoteDataSource
import com.dicoding.mymovies.data.source.remote.response.DetailMoviesResponse
import com.dicoding.mymovies.data.source.remote.response.DetailTvShowResponse
import com.dicoding.mymovies.data.source.remote.response.Movies
import com.dicoding.mymovies.data.source.remote.response.TvShow
import com.dicoding.mymovies.utils.AppExecutors
import com.dicoding.mymovies.vo.Resource
import java.lang.StringBuilder

class FakeMoviesRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
    ) : MoviesDataSource {

    override fun getMovies(sort: String): LiveData<Resource<PagedList<MoviesEntity>>> {
        return object : NetworkBoundResource<PagedList<MoviesEntity>, List<Movies>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MoviesEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()

                return LivePagedListBuilder(localDataSource.getAllMovies(sort), config).build()
            }

            override fun shouldFetch(data: PagedList<MoviesEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<Movies>>> =
                remoteDataSource.getMovies()

            override fun saveCallResult(data: List<Movies>) {
                val moviesList = ArrayList<MoviesEntity>()
                for (response in data) {
                    val movies = MoviesEntity(
                        id = response.id,
                        voteAverage = response.voteAverage,
                        runtime = 0,
                        genres = "",
                        releaseDate = response.releaseDate,
                        posterPath = response.posterPath,
                        favorite = false,
                        overview = response.overview,
                        title = response.title
                    )
                    moviesList.add(movies)
                }
                localDataSource.insertMovies(moviesList)
            }
        }.asLiveData()
    }

    override fun getDetailMovies(moviesId: Int): LiveData<Resource<MoviesEntity>> {
        return object : NetworkBoundResource<MoviesEntity, DetailMoviesResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<MoviesEntity> =
                localDataSource.getMoviesById(moviesId)

            override fun shouldFetch(data: MoviesEntity?): Boolean =
                data != null && data.runtime == 0 && data.genres == ""

            override fun createCall(): LiveData<ApiResponse<DetailMoviesResponse>> =
                remoteDataSource.getDetailMovies(moviesId.toString())

            override fun saveCallResult(data: DetailMoviesResponse) {
                val genres = StringBuilder(moviesId.toString())

                for (i in data.genres.indices) {
                    if (i< data.genres.size - 1) {
                        genres.append("${data.genres[i].name}, ")
                    } else {
                        genres.append(data.genres[i].name)
                    }
                }

                val movies = MoviesEntity(
                    id = data.id,
                    title = data.title,
                    runtime = data.runtime,
                    genres = genres.toString(),
                    overview = data.overview,
                    favorite = false,
                    posterPath = data.posterPath,
                    releaseDate = data.releaseDate,
                    voteAverage = data.voteAverage
                )
                localDataSource.updateMovies(movies, false)
            }
        }.asLiveData()
    }

    override fun getFavoriteMovies(): LiveData<PagedList<MoviesEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        return LivePagedListBuilder(localDataSource.getFavoriteMovies(), config).build()
    }

    override fun setFavoriteMovies(movies: MoviesEntity, state: Boolean) {
        localDataSource.setFavoriteMovies(movies, state)
    }

    override fun getTvShow(sort: String): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object : NetworkBoundResource<PagedList<TvShowEntity>, List<TvShow>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()

                return LivePagedListBuilder(localDataSource.getAllTvShow(sort), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShow>>> =
                remoteDataSource.getTvShow()

            override fun saveCallResult(data: List<TvShow>) {
                val tvShowList = ArrayList<TvShowEntity>()
                for (response in data) {
                    val tvShow = TvShowEntity(
                        id = response.id,
                        name = response.name,
                        runtime = 0,
                        overview = response.overview,
                        voteAverage = response.voteAverage,
                        releaseDate = response.firstAirDate,
                        posterPath = response.posterPath,
                        favorite = false,
                        genres = ""
                    )
                    tvShowList.add(tvShow)
                }
                localDataSource.insertTvShow(tvShowList)
            }
        }.asLiveData()

    }

    override fun getDetailTvShow(tvShowId: Int): LiveData<Resource<TvShowEntity>> {
        return object : NetworkBoundResource<TvShowEntity, DetailTvShowResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<TvShowEntity> =
                localDataSource.getTvShowById(tvShowId)

            override fun shouldFetch(data: TvShowEntity?): Boolean =
                data != null && data.runtime == 0 && data.genres == ""

            override fun createCall(): LiveData<ApiResponse<DetailTvShowResponse>> =
                remoteDataSource.getDetailTvShow(tvShowId.toString())

            override fun saveCallResult(data: DetailTvShowResponse) {
                val genres = StringBuilder().append("")

                for (i in data.genres.indices) {
                    if (i < data.genres.size - 1) {
                        genres.append("${data.genres[i].name}, ")
                    } else {
                        genres.append(data.genres[i].name)
                    }
                }

                val tvShow = TvShowEntity(
                    id = data.id,
                    name = data.name,
                    genres = genres.toString(),
                    overview = data.overview,
                    favorite = false,
                    posterPath = data.posterPath,
                    releaseDate = data.firstAirDate,
                    voteAverage = data.voteAverage,
                    runtime = data.episodeRunTime.first()
                )
                localDataSource.updateTvShow(tvShow, false)
            }
        }.asLiveData()
    }

    override fun getFavoriteTvShow(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        return LivePagedListBuilder(localDataSource.getFavoriteTvShow(), config).build()
    }

    override fun setFavoriteTvShow(tvShow: TvShowEntity, state: Boolean) {
        localDataSource.setFavoriteTvShow(tvShow, state)
    }

}