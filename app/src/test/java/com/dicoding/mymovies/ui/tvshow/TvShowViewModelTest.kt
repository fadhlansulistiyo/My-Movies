package com.dicoding.mymovies.ui.tvshow


import com.dicoding.mymovies.data.source.MoviesRepository
import com.dicoding.mymovies.data.source.local.entity.TvShowEntity

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.mymovies.vo.Resource
import org.junit.Test
import org.mockito.Mockito.verify

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TvShowEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowEntity>

    @Before
    fun setup() {
        viewModel = TvShowViewModel(moviesRepository)
    }

    @Test
    fun getTvShow() {
        val dummyTvShow = Resource.success(pagedList)
        `when`(dummyTvShow.data?.size).thenReturn(3)
        val tvShow = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        tvShow.value = dummyTvShow

        `when`(moviesRepository.getTvShow("BEST")).thenReturn(tvShow)
        val tvShows = viewModel.getTvShow("BEST").value?.data
        verify(moviesRepository).getTvShow("BEST")
        assertNotNull(tvShows)
        assertEquals(3, tvShows?.size)

        viewModel.getTvShow("BEST").observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }
}