package com.dicoding.mymovies.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.dicoding.mymovies.data.source.local.LocalDataSource
import com.dicoding.mymovies.data.source.local.entity.MoviesEntity
import com.dicoding.mymovies.data.source.local.entity.TvShowEntity
import com.dicoding.mymovies.data.source.remote.RemoteDataSource
import com.dicoding.mymovies.utils.AppExecutors
import com.dicoding.mymovies.utils.DataDummy
import com.dicoding.mymovies.utils.LiveDataTestUtil
import com.dicoding.mymovies.utils.PagedListUtil
import com.dicoding.mymovies.vo.Resource
import org.junit.Test
import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions

class MoviesRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val moviesRepository = FakeMoviesRepository(remote, local, appExecutors)

    private val moviesResponse = DataDummy.generateRemoteDummyMovies()
    private val moviesId = moviesResponse[0].id
    private val moviesDetail = DataDummy.generateRemoteDetailMovies()

    private val tvShowResponse = DataDummy.generateRemoteTvShow()
    private val tvShowId = tvShowResponse[0].id
    private val tvShowDetail = DataDummy.generateRemoteDetailTvShow()

    @Test
    fun getMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MoviesEntity>
        `when`(local.getAllMovies("BEST")).thenReturn(dataSourceFactory)
        moviesRepository.getMovies("BEST")

        val moviesEntity = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getAllMovies("BEST")
        assertNotNull(moviesEntity)
        assertEquals(moviesResponse.size, moviesEntity.data?.size)
    }

    @Test
    fun getDetailMovies() {
        val dummyDetail = MutableLiveData<MoviesEntity>()
        dummyDetail.value = DataDummy.getDetailMovies()
        `when`(local.getMoviesById(moviesId)).thenReturn(dummyDetail)

        val moviesDetailEntity = LiveDataTestUtil.getValue(moviesRepository.getDetailMovies(moviesId))
        verify(local).getMoviesById(moviesId)
        assertNotNull(moviesDetailEntity)
        assertEquals(moviesDetail.id, moviesDetailEntity.data?.id)
    }

    @Test
    fun getFavoriteMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MoviesEntity>
        `when`(local.getFavoriteMovies()).thenReturn(dataSourceFactory)
        moviesRepository.getFavoriteMovies()

        val moviesEntity = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getFavoriteMovies()
        assertNotNull(moviesEntity)
        assertEquals(moviesResponse.size, moviesEntity.data?.size)
    }

    @Test
    fun setFavoriteMovies() {
        moviesRepository.setFavoriteMovies(DataDummy.getDetailMovies(), true)
        verify(local).setFavoriteMovies(DataDummy.getDetailMovies(), true)
        verifyNoMoreInteractions(local)
    }

    //-----------------------------------------------------

    @Test
    fun getTvShow() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getAllTvShow("BEST")).thenReturn(dataSourceFactory)
        moviesRepository.getTvShow("BEST")

        val tvShowEntity = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTvshow()))
        verify(local).getAllTvShow("BEST")
        assertNotNull(tvShowEntity)
        assertEquals(tvShowResponse.size, tvShowEntity.data?.size)
    }

    @Test
    fun getDetailTvShow() {
        val dummyDetail = MutableLiveData<TvShowEntity>()
        dummyDetail.value = DataDummy.getDetailTvShow()
        `when`(local.getTvShowById(tvShowId)).thenReturn(dummyDetail)

        val tvShowDetailEntity = LiveDataTestUtil.getValue(moviesRepository.getDetailTvShow(tvShowId))
        verify(local).getTvShowById(tvShowId)
        assertNotNull(tvShowDetailEntity)
        assertEquals(tvShowDetail.id, tvShowDetailEntity.data?.id)
    }

    @Test
    fun getFavoriteTvShow() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getFavoriteTvShow()).thenReturn(dataSourceFactory)
        moviesRepository.getFavoriteTvShow()

        val tvShowEntity = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTvshow()))
        verify(local).getFavoriteTvShow()
        assertNotNull(tvShowEntity)
        assertEquals(tvShowResponse.size, tvShowEntity.data?.size)
    }

    @Test
    fun setFavoriteTvShow() {
        moviesRepository.setFavoriteTvShow(DataDummy.getDetailTvShow(), true)
        verify(local).setFavoriteTvShow(DataDummy.getDetailTvShow(), true)
        verifyNoMoreInteractions(local)
    }
}