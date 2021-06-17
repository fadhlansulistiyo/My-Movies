package com.dicoding.mymovies.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.sqlite.db.SimpleSQLiteQuery
import com.dicoding.mymovies.data.source.local.entity.MoviesEntity
import androidx.paging.DataSource
import androidx.room.*
import com.dicoding.mymovies.data.source.local.entity.TvShowEntity

@Dao
interface MoviesDao {
    @RawQuery(observedEntities = [MoviesEntity::class])
    fun getMovies(query: SimpleSQLiteQuery): DataSource.Factory<Int, MoviesEntity>

    @Query("SELECT * FROM movies_entities WHERE id = :id")
    fun getMoviesById(id: Int) : LiveData<MoviesEntity>

    @Query("SELECT * FROM movies_entities WHERE favorite = 1")
    fun getFavoriteMovies(): DataSource.Factory<Int, MoviesEntity>

    @RawQuery(observedEntities = [TvShowEntity::class])
    fun getTvShow(query: SimpleSQLiteQuery) : DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM tv_show_entities WHERE id = :id")
    fun getTvShowById(id: Int) : LiveData<TvShowEntity>

    @Query("SELECT * FROM tv_show_entities WHERE favorite = 1")
    fun getFavoriteTvShow() : DataSource.Factory<Int, TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MoviesEntity>)

    @Update
    fun updateMovies(movies: MoviesEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(tvShow: List<TvShowEntity>)

    @Update
    fun updateTvShow(tvShow: TvShowEntity)
}