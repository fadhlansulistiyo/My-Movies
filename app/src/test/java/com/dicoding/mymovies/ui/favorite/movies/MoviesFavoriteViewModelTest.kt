package com.dicoding.mymovies.ui.favorite.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.dicoding.mymovies.data.source.MoviesRepository
import com.dicoding.mymovies.data.source.local.entity.MoviesEntity
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import androidx.lifecycle.Observer
import com.dicoding.mymovies.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.mockito.Mockito.`when`

@RunWith(MockitoJUnitRunner::class)
class MoviesFavoriteViewModelTest {

    private lateinit var viewModel: MoviesFavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Mock
    private lateinit var observer: Observer<PagedList<MoviesEntity>>

    @Mock
    private lateinit var pagedList: PagedList<MoviesEntity>

    @Before
    fun setup() {
        viewModel = MoviesFavoriteViewModel(moviesRepository)
    }

    @Test
    fun getFavoriteMovies() {
        val dummyMovies = pagedList
        `when`(dummyMovies.size).thenReturn(3)
        val movies = MutableLiveData<PagedList<MoviesEntity>>()
        movies.value = dummyMovies

        `when`(moviesRepository.getFavoriteMovies()).thenReturn(movies)
        val movie = viewModel.getFavoriteMovies().value
        verify(moviesRepository).getFavoriteMovies()
        assertNotNull(movie)
        assertEquals(3, movie?.size)

        viewModel.getFavoriteMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

    @Test
    fun setFavoriteMovies() {
        viewModel.setFavoriteMovies(DataDummy.getDetailMovies())
        verify(moviesRepository).setFavoriteMovies(DataDummy.getDetailMovies(), true)
        verifyNoMoreInteractions(moviesRepository)
    }
}