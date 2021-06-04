package com.dicoding.mymovies.ui.detail

import com.dicoding.mymovies.utils.DataDummy
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class DetailMoviesViewModelTest {

    private val dummyMovies = DataDummy.generateDummyMovies()[0]
    private val moviesId = dummyMovies.moviesId

    private val dummyTvShow = DataDummy.generateDummyTvshow()[0]
    private val tvShowId = dummyTvShow.tvShowId

    private lateinit var viewModel: DetailMoviesViewModel

    @Before
    fun setup() {
        viewModel = DetailMoviesViewModel()
    }

    @Test
    fun getDetailMovies() {
        val movies = viewModel.getMovies()
        val detailMovies = viewModel.getDetailMovies(moviesId, movies as ArrayList)
        assertNotNull(detailMovies)
        assertEquals(dummyMovies.title, detailMovies.title)
        assertEquals(dummyMovies.releaseDate, detailMovies.releaseDate)
        assertEquals(dummyMovies.genre, detailMovies.genre)
        assertEquals(dummyMovies.rating, detailMovies.rating)
        assertEquals(dummyMovies.duration, detailMovies.duration)
        assertEquals(dummyMovies.overview, detailMovies.overview)
        assertEquals(dummyMovies.image, detailMovies.image)
    }

    @Test
    fun getDetailTvShow() {
        val tvShow = viewModel.getTvShow()
        val detailTvShow = viewModel.getDetailTvShow(tvShowId, tvShow as ArrayList)
        assertNotNull(detailTvShow)
        assertEquals(dummyTvShow.title, detailTvShow.title)
        assertEquals(dummyTvShow.releaseDate, detailTvShow.releaseDate)
        assertEquals(dummyTvShow.genre, detailTvShow.genre)
        assertEquals(dummyTvShow.rating, detailTvShow.rating)
        assertEquals(dummyTvShow.episode, detailTvShow.episode)
        assertEquals(dummyTvShow.overview, detailTvShow.overview)
        assertEquals(dummyTvShow.image, detailTvShow.image)
    }

    @Test
    fun getMovies() {
        val moviesEntities = viewModel.getMovies()
        assertNotNull(moviesEntities)
        assertEquals(10, moviesEntities.size)
    }

    @Test
    fun getTvShow() {
        val tvShowEntities = viewModel.getTvShow()
        assertNotNull(tvShowEntities)
        assertEquals(10, tvShowEntities.size)
    }
}