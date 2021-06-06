package com.dicoding.mymovies.ui.movies

import com.dicoding.mymovies.data.source.MoviesRepository
import org.junit.Test

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
import org.mockito.Mockito.verify

import com.dicoding.mymovies.data.source.local.entity.MoviesEntity
import com.dicoding.mymovies.utils.DataDummy

@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest {

    private lateinit var viewModel: MoviesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Mock
    private lateinit var observer: Observer<List<MoviesEntity>>

    @Before
    fun setup() {
        viewModel = MoviesViewModel(moviesRepository)
    }

    @Test
    fun getMovies() {
        val dummyMovies = DataDummy.generateDummyMovies()
        val movies = MutableLiveData<List<MoviesEntity>>()
        movies.value = dummyMovies

        `when`(moviesRepository.getMovies()).thenReturn(movies)
        val movie = viewModel.getMovies().value
        verify(moviesRepository).getMovies()
        assertNotNull(movie)
        assertEquals(3, movie?.size)

        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}