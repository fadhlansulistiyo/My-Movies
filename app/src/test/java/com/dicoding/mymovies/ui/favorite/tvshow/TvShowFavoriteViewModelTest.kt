package com.dicoding.mymovies.ui.favorite.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.mymovies.data.source.MoviesRepository
import com.dicoding.mymovies.data.source.local.entity.TvShowEntity
import com.dicoding.mymovies.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowFavoriteViewModelTest {

    private lateinit var viewModel: TvShowFavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Mock
    private lateinit var observer: Observer<PagedList<TvShowEntity>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowEntity>

    @Before
    fun setup() {
        viewModel = TvShowFavoriteViewModel(moviesRepository)
    }

    @Test
    fun getFavoriteTvShow() {
        val dummyTvShow = pagedList
        Mockito.`when`(dummyTvShow.size).thenReturn(3)
        val tvShow = MutableLiveData<PagedList<TvShowEntity>>()
        tvShow.value = dummyTvShow

        Mockito.`when`(moviesRepository.getFavoriteTvShow()).thenReturn(tvShow)
        val tvShows = viewModel.getFavoriteTvShow().value
        verify(moviesRepository).getFavoriteTvShow()
        Assert.assertNotNull(tvShows)
        Assert.assertEquals(3, tvShows?.size)

        viewModel.getFavoriteTvShow().observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }

    @Test
    fun setFavoriteTvShow() {
        viewModel.setFavoriteTvShow(DataDummy.getDetailTvShow())
        verify(moviesRepository).setFavoriteTvShow(DataDummy.getDetailTvShow(), true)
        verifyNoMoreInteractions(moviesRepository)
    }
}