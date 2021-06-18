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
import androidx.paging.PagedList
import org.mockito.Mockito.verify

import com.dicoding.mymovies.data.source.local.entity.MoviesEntity
import com.dicoding.mymovies.utils.DataDummy
import com.dicoding.mymovies.vo.Resource

@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest {

    private lateinit var viewModel: MoviesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MoviesEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<MoviesEntity>

    @Before
    fun setup() {
        viewModel = MoviesViewModel(moviesRepository)
    }

    @Test
    fun getMovies() {
        val dummyMovies = Resource.success(pagedList)
        `when`(dummyMovies.data?.size).thenReturn(3)
        val movies = MutableLiveData<Resource<PagedList<MoviesEntity>>>()
        movies.value = dummyMovies

        `when`(moviesRepository.getMovies("BEST")).thenReturn(movies)
        val movie = viewModel.getMovies("BEST").value?.data
        verify(moviesRepository).getMovies("BEST")
        assertNotNull(movie)
        assertEquals(3, movie?.size)

        viewModel.getMovies("BEST").observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}