package com.dicoding.mymovies.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.dicoding.mymovies.data.source.MoviesRepository
import com.dicoding.mymovies.data.source.local.entity.DetailEntity
import com.dicoding.mymovies.utils.DataDummy
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.Mockito.`when`
import androidx.lifecycle.Observer
import com.dicoding.mymovies.ui.detail.DetailMoviesViewModel.Companion.MOVIES
import com.dicoding.mymovies.ui.detail.DetailMoviesViewModel.Companion.TV_SHOW
import org.junit.runner.RunWith
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailMoviesViewModelTest {

    private val dummyMovies = DataDummy.getDetailMovies()
    private val moviesId = dummyMovies.id.toString()

    private val dummyTvShow = DataDummy.getDetailTvShow()
    private val tvShowId = dummyTvShow.id.toString()

    private lateinit var viewModel: DetailMoviesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Mock
    private lateinit var moviesObserver: Observer<DetailEntity>

    @Before
    fun setupMoviesDetail() {
        viewModel = DetailMoviesViewModel(moviesRepository)
    }

    @Test
    fun getDetailMovies() {
        val movie = MutableLiveData<DetailEntity>()
        movie.value = dummyMovies

        `when`(moviesRepository.getDetailMovies(moviesId)).thenReturn(movie)
        viewModel.setMoviesTvShow(moviesId, MOVIES)
        val detailEntity = viewModel.getDetailData().value as DetailEntity
        verify(moviesRepository).getDetailMovies(moviesId)

        assertNotNull(detailEntity)
        assertEquals(dummyMovies.id, detailEntity.id)
        assertEquals(dummyMovies.title, detailEntity.title)
        assertEquals(dummyMovies.releaseDate, detailEntity.releaseDate)
        assertEquals(dummyMovies.genres, detailEntity.genres)
        assertEquals(dummyMovies.voteAverage.toInt(), detailEntity.voteAverage.toInt())
        assertEquals(dummyMovies.runtime, detailEntity.runtime)
        assertEquals(dummyMovies.overview, detailEntity.overview)
        assertEquals(dummyMovies.posterPath, detailEntity.posterPath)

        viewModel.getDetailData().observeForever(moviesObserver)
        verify(moviesObserver).onChanged(dummyMovies)
    }

    @Before
    fun setupTvShow() {
        viewModel = DetailMoviesViewModel(moviesRepository)
    }

    @Test
    fun getDetailTvShow() {
        val tvShow = MutableLiveData<DetailEntity>()
        tvShow.value = dummyTvShow

        `when`(moviesRepository.getDetailTvShow(tvShowId)).thenReturn(tvShow)
        viewModel.setMoviesTvShow(tvShowId, TV_SHOW)
        val detailEntity = viewModel.getDetailData().value as DetailEntity
        verify(moviesRepository).getDetailTvShow(tvShowId)

        assertNotNull(detailEntity)
        assertEquals(dummyTvShow.id, detailEntity.id)
        assertEquals(dummyTvShow.title, detailEntity.title)
        assertEquals(dummyTvShow.releaseDate, detailEntity.releaseDate)
        assertEquals(dummyTvShow.genres, detailEntity.genres)
        assertEquals(dummyTvShow.voteAverage.toInt(), detailEntity.voteAverage.toInt())
        assertEquals(dummyTvShow.runtime, detailEntity.runtime)
        assertEquals(dummyTvShow.overview, detailEntity.overview)
        assertEquals(dummyTvShow.posterPath, detailEntity.posterPath)

        viewModel.getDetailData().observeForever(moviesObserver)
        verify(moviesObserver).onChanged(dummyTvShow)
    }
}