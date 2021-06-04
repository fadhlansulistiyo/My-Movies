package com.dicoding.mymovies.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.mymovies.R
import com.dicoding.mymovies.data.MoviesEntity
import com.dicoding.mymovies.data.TvShowEntity
import com.dicoding.mymovies.databinding.ActivityDetailMoviesBinding
import com.dicoding.mymovies.databinding.ContentDetailMoviesBinding

class DetailMoviesActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
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

        val id = intent.getIntExtra(EXTRA_DETAIL, 0)
        val type = intent.getStringExtra(EXTRA_TYPE)

        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailMoviesViewModel::class.java]

        if (type == TYPE_MOVIE) {
            val listMovies = viewModel.getMovies()
            val movies = viewModel.getDetailMovies(id, listMovies as ArrayList)

            supportActionBar?.title = "Detail Movies"
            showDetailMovies(movies)

        } else if (type == TYPE_TVSHOW) {
            val listTvShow = viewModel.getTvShow()
            val tvShow = viewModel.getDetailTvShow(id, listTvShow as ArrayList)

            supportActionBar?.title = "Tv Shows"
            showDetailTvShow(tvShow)
        }
    }

    private fun showDetailMovies(moviesEntity: MoviesEntity) {
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
    }

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