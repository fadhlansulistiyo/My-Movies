package com.dicoding.mymovies.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dicoding.mymovies.data.source.local.entity.DetailEntity
import com.dicoding.mymovies.databinding.ActivityDetailMoviesBinding
import com.dicoding.mymovies.databinding.ContentDetailMoviesBinding
import com.dicoding.mymovies.utils.ConstantValue.BASE_URL_IMAGE
import com.dicoding.mymovies.viewmodel.ViewModelFactory

class DetailMoviesActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
        const val EXTRA_FILM = "extra_film"
        const val EXTRA_CATEGORY = "extra_category"
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
}