package com.dicoding.mymovies.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dicoding.mymovies.data.source.remote.RemoteDataSource
import com.dicoding.mymovies.utils.DataDummy
import com.dicoding.mymovies.utils.LiveDataTestUtil
import org.junit.Test
import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mockito.doAnswer
import org.mockito.Mockito.mock
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.eq

class MoviesRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val moviesRepository = FakeMoviesRepository(remote)

    private val moviesResponse = DataDummy.generateRemoteDummyMovies()
    private val moviesId = moviesResponse[0].id.toString()
    private val moviesDetail = DataDummy.generateRemoteDetailMovies()

    private val tvShowResponse = DataDummy.generateRemoteTvShow()
    private val tvShowId = tvShowResponse[0].id.toString()
    private val tvShowDetail = DataDummy.generateRemoteDetailTvShow()

    @Test
    fun getMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback).onMoviesLoaded(moviesResponse)
            null
        }.`when`(remote).getMovies(any())

        val moviesEntities = LiveDataTestUtil.getValue(moviesRepository.getMovies())
        verify(remote).getMovies(any())
        assertNotNull(moviesEntities)
        assertEquals(moviesResponse.size, moviesEntities.size)
    }

    @Test
    fun getDetailMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadDetailMoviesCallback).onDetailMoviesLoaded(moviesDetail)
            null
        }.`when`(remote).getDetailMovies(any(), eq(moviesId))

        val moviesDetailEntity = LiveDataTestUtil.getValue(moviesRepository.getDetailMovies(moviesId))
        verify(remote).getDetailMovies(any(), eq(moviesId))
        assertNotNull(moviesDetailEntity)
        assertEquals(moviesDetail.id, moviesDetailEntity.id)
    }

    @Test
    fun getTvShow() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvShowCallback).onTvShowLoaded(tvShowResponse)
            null
        }.`when`(remote).getTvShow(any())

        val tvShowEntities = LiveDataTestUtil.getValue(moviesRepository.getTvShow())
        verify(remote).getTvShow(any())
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponse.size, tvShowEntities.size)
    }

    @Test
    fun getDetailTvShow() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadDetailTvShowCallback).onDetailTvShowLoaded(tvShowDetail)
            null
        }.`when`(remote).getDetailTvShow(any(), eq(tvShowId))

        val tvShowDetailEntity = LiveDataTestUtil.getValue(moviesRepository.getDetailTvShow(tvShowId))
        verify(remote).getDetailTvShow(any(), eq(tvShowId))
        assertNotNull(tvShowDetailEntity)
        assertEquals(tvShowDetail.id, tvShowDetailEntity.id)
    }
}