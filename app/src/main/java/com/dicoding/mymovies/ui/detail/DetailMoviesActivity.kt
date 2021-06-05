package com.dicoding.mymovies.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.mymovies.R
import com.dicoding.mymovies.data.source.local.entity.DetailEntity
import com.dicoding.mymovies.data.source.local.entity.MoviesEntity
import com.dicoding.mymovies.data.source.local.entity.TvShowEntity
import com.dicoding.mymovies.databinding.ActivityDetailMoviesBinding
import com.dicoding.mymovies.databinding.ContentDetailMoviesBinding
import com.dicoding.mymovies.utils.ConstantValue.BASE_URL_IMAGE
import com.dicoding.mymovies.viewmodel.ViewModelFactory

class DetailMoviesActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
        const val EXTRA_FILM = "extra_film"
        const val EXTRA_CATEGORY = "extra_category"

        const val EXTRA_TYPE = "extra_type"
        const val TYPE_MOVIE = "extra_movie"
        const val TYPE_TVSHOW = "extra_tv"
    }

    private lateinit var detailContentBinding: ContentDetailMoviesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailMoviesBinding = ActivityDetailMoviesBinding.inflate(layoutInflater)
        detailContentBinding = activityDetailMoviesBinding.detailContent
        setContentView(activityDetailMoviesBinding.root)

        setSupportActionBar(activityDetailMoviesBinding.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        detailContentBinding.progressBar.visibility = View.VISIBLE

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailMoviesViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val filmId = extras.getString(EXTRA_FILM)
            val filmCategory = extras.getString(EXTRA_CATEGORY)

            if (filmId != null && filmCategory != null) {
                viewModel.setMoviesTvShow(filmId, filmCategory)
                viewModel.getDetailData().observe(this, { detail ->
                    supportActionBar?.title = "Detail"
                    populateFilm(detail)
                })
            }
        }

        // -----------------------------------------------------
        /*if (type == TYPE_MOVIE) {
            val listMovies = viewModel.getMovies()
            val movies = viewModel.getDetailMovies(id, listMovies as ArrayList)

            supportActionBar?.title = "Detail Movies"
            showDetailMovies(movies)

        } else if (type == TYPE_TVSHOW) {
            val listTvShow = viewModel.getTvShow()
            val tvShow = viewModel.getDetailTvShow(id, listTvShow as ArrayList)

            supportActionBar?.title = "Tv Shows"
            showDetailTvShow(tvShow)
        }*/
        // -----------------------------------------------------
    }

    private fun populateFilm(data: DetailEntity) {
        val genre = data.genres.toString().replace("[", "").replace("]", "")

        detailContentBinding.tvDuratingEps.text = data.runtime.toString()
        detailContentBinding.tvOverview.text = data.overview
        detailContentBinding.tvRating.text = data.voteAverage.toString()
        detailContentBinding.tvGenre.text = genre
        detailContentBinding.tvReleaseDate.text = data.releaseDate
        detailContentBinding.tvTitle.text = data.title

        Glide.with(this)
            .load(BASE_URL_IMAGE + data.posterPath)
            .into(detailContentBinding.imgPoster)

        detailContentBinding.progressBar.visibility = View.INVISIBLE
    }

    /*private fun showDetailMovies(moviesEntity: MoviesEntity) {
        detailContentBinding.tvTitle.text = moviesEntity.title
        detailContentBinding.tvReleaseDate.text = moviesEntity.releaseDate
        detailContentBinding.tvGenre.text = moviesEntity.genre
        detailContentBinding.tvRating.text = moviesEntity.voteAverage
        detailContentBinding.tvDuratingEps.text = moviesEntity.duration
        detailContentBinding.tvOverview.text = moviesEntity.overview

        Glide.with(this)
            .load(moviesEntity.posterPath)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(detailContentBinding.imgPoster)
    }

    private fun showDetailTvShow(tvShow: TvShowEntity) {
        detailContentBinding.tvTitle.text = tvShow.title
        detailContentBinding.tvReleaseDate.text = tvShow.releaseDate
        detailContentBinding.tvGenre.text = tvShow.genre
        detailContentBinding.tvRating.text = tvShow.rating
        detailContentBinding.tvDuratingEps.text = tvShow.episode
        detailContentBinding.tvOverview.text = tvShow.overview

        Glide.with(this)
            .load(tvShow.image)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(detailContentBinding.imgPoster)
    }*/

    /*private fun populateMovies(moviesEntity: MoviesEntity) {
        detailContentBinding.tvTitle.text = moviesEntity.title
        detailContentBinding.tvReleaseDate.text = moviesEntity.releaseDate
        detailContentBinding.tvGenre.text = moviesEntity.genre
        detailContentBinding.tvRating.text = moviesEntity.rating
        detailContentBinding.tvDuratingEps.text = moviesEntity.duration
        detailContentBinding.tvOverview.text = moviesEntity.overview

        Glide.with(this)
            .load(moviesEntity.image)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(detailContentBinding.imgPoster)
    }*/
}