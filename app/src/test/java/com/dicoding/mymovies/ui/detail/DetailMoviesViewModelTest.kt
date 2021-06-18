package com.dicoding.mymovies.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.dicoding.mymovies.data.source.MoviesRepository
import com.dicoding.mymovies.utils.DataDummy
import org.junit.Test
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.Mockito.`when`
import androidx.lifecycle.Observer
import com.dicoding.mymovies.data.source.local.entity.MoviesEntity
import com.dicoding.mymovies.data.source.local.entity.TvShowEntity
import com.dicoding.mymovies.ui.detail.DetailMoviesViewModel.Companion.MOVIES
import com.dicoding.mymovies.ui.detail.DetailMoviesViewModel.Companion.TV_SHOW
import com.dicoding.mymovies.vo.Resource
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.runner.RunWith
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailMoviesViewModelTest {

    private val dummyMovies = DataDummy.getDetailMovies()
    private val moviesId = dummyMovies.id

    private val dummyTvShow = DataDummy.getDetailTvShow()
    private val tvShowId = dummyTvShow.id

    private lateinit var viewModel: DetailMoviesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Mock
    private lateinit var moviesObserver: Observer<Resource<MoviesEntity>>

    @Mock
    private lateinit var tvShowObserver: Observer<Resource<TvShowEntity>>

    @Before
    fun setupMoviesDetail() {
        viewModel = DetailMoviesViewModel(moviesRepository)
    }

    @Test
    fun getDetailMovies() {
        val dummyDetailMovies = Resource.success(DataDummy.getDetailMovies())
        val movies = MutableLiveData<Resource<MoviesEntity>>()
        movies.value = dummyDetailMovies

        `when`(moviesRepository.getDetailMovies(moviesId)).thenReturn(movies)
        viewModel.setMoviesTvShow(moviesId.toString(), MOVIES)
        viewModel.getDetailMovies().observeForever(moviesObserver)
        verify(moviesObserver).onChanged(dummyDetailMovies)
    }

    @Test
    fun setFavoriteMovies() {
        val dummyDetailMovies = Resource.success(DataDummy.getDetailMovies())
        val movies = MutableLiveData<Resource<MoviesEntity>>()
        movies.value = dummyDetailMovies

        `when`(moviesRepository.getDetailMovies(moviesId)).thenReturn(movies)
        viewModel.setMoviesTvShow(moviesId.toString(), MOVIES)
        viewModel.setFavoriteMovies()
        verify(moviesRepository).setFavoriteMovies(movies.value!!.data as MoviesEntity, true)
        verifyNoMoreInteractions(moviesObserver)
    }

    @Before
    fun setupTvShowDetail() {
        viewModel = DetailMoviesViewModel(moviesRepository)
    }

    @Test
    fun getDetailTvShow() {
        val dummyDetailTvShow = Resource.success(DataDummy.getDetailTvShow())
        val tvShow = MutableLiveData<Resource<TvShowEntity>>()
        tvShow.value = dummyDetailTvShow

        `when`(moviesRepository.getDetailTvShow(tvShowId)).thenReturn(tvShow)
        viewModel.setMoviesTvShow(tvShowId.toString(), TV_SHOW)
        viewModel.getDetailTvShow().observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyDetailTvShow)
    }

    @Test
    fun setFavoriteTvShow() {
       val dummyDetailTvShow = Resource.success(DataDummy.getDetailTvShow())
        val tvShow = MutableLiveData<Resource<TvShowEntity>>()
        tvShow.value = dummyDetailTvShow

        `when`(moviesRepository.getDetailTvShow(tvShowId)).thenReturn(tvShow)
        viewModel.setMoviesTvShow(tvShowId.toString(), TV_SHOW)
        viewModel.setFavoriteTvShow()
        verify(moviesRepository).setFavoriteTvShow(tvShow.value!!.data as TvShowEntity, true)
        verifyNoMoreInteractions(tvShowObserver)
    }
}