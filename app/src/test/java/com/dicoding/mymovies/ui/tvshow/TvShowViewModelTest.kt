package com.dicoding.mymovies.ui.tvshow


import com.dicoding.mymovies.data.source.MoviesRepository
import com.dicoding.mymovies.data.source.local.entity.TvShowEntity
import com.dicoding.mymovies.utils.DataDummy

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
    private lateinit var observer: Observer<List<TvShowEntity>>

    @Before
    fun setup() {
        viewModel = TvShowViewModel(moviesRepository)
    }

    @Test
    fun getTvShow() {
        val dummyTvShow = DataDummy.generateDummyTvshow()
        val tvShow = MutableLiveData<List<TvShowEntity>>()
        tvShow.value = dummyTvShow

        `when`(moviesRepository.getTvShow()).thenReturn(tvShow)
        val tvShows = viewModel.getTvShow().value
        verify(moviesRepository).getTvShow()
        assertNotNull(tvShows)
        assertEquals(3, tvShows?.size)

        viewModel.getTvShow().observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }
}