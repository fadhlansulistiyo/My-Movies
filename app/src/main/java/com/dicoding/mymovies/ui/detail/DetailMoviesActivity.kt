package com.dicoding.mymovies.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dicoding.mymovies.R
import com.dicoding.mymovies.data.source.local.entity.MoviesEntity
import com.dicoding.mymovies.data.source.local.entity.TvShowEntity
import com.dicoding.mymovies.databinding.ActivityDetailMoviesBinding
import com.dicoding.mymovies.ui.detail.DetailMoviesViewModel.Companion.MOVIES
import com.dicoding.mymovies.ui.detail.DetailMoviesViewModel.Companion.TV_SHOW
import com.dicoding.mymovies.utils.ConstantValue.BASE_URL_IMAGE
import com.dicoding.mymovies.viewmodel.ViewModelFactory
import com.dicoding.mymovies.vo.Status

class DetailMoviesActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val EXTRA_FILM = "extra_film"
        const val EXTRA_CATEGORY = "extra_category"
    }

    private lateinit var detailBinding: ActivityDetailMoviesBinding
    private lateinit var viewModel: DetailMoviesViewModel
    private var filmCategory: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailMoviesBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)

        setSupportActionBar(detailBinding.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        detailBinding.progressBar.visibility = View.VISIBLE

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailMoviesViewModel::class.java]

        detailBinding.iconAddFavorite.setOnClickListener(this)

        val extras = intent.extras
        if (extras != null) {
            val filmId = extras.getString(EXTRA_FILM)
            filmCategory = extras.getString(EXTRA_CATEGORY)

            if (filmId != null && filmCategory != null) {
                viewModel.setMoviesTvShow(filmId, filmCategory.toString())
                setState()
                if (filmCategory == MOVIES) {
                    viewModel.getDetailMovies().observe(this, { detailFilm ->
                        when (detailFilm.status) {
                            Status.LOADING -> detailBinding.progressBar.visibility = View.VISIBLE
                            Status.SUCCESS -> {
                                if (detailFilm.data != null) {
                                    detailBinding.progressBar.visibility = View.INVISIBLE
                                    populateFilmDetail(detailFilm.data)
                                }
                            }
                            Status.ERROR -> {
                                detailBinding.progressBar.visibility = View.INVISIBLE
                                Toast.makeText(applicationContext, "Error to Load Movies", Toast.LENGTH_SHORT).show()
                            }
                        }
                    })
                } else if (filmCategory == TV_SHOW) {
                    viewModel.getDetailTvShow().observe(this, { detailFilm ->
                        when (detailFilm.status) {
                            Status.LOADING -> detailBinding.progressBar.visibility = View.VISIBLE
                            Status.SUCCESS -> {
                                if (detailFilm.data != null) {
                                    detailBinding.progressBar.visibility = View.INVISIBLE
                                    populateFilmDetail(detailFilm.data)
                                }
                            }
                            Status.ERROR -> {
                                detailBinding.progressBar.visibility = View.INVISIBLE
                                Toast.makeText(applicationContext, "Error to Load Tv Show", Toast.LENGTH_SHORT).show()
                            }
                        }
                    })
                }
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.icon_add_favorite -> {
                onIconAddFavoriteClicked()
            }
        }
    }

    private fun onIconAddFavoriteClicked() {
        if (filmCategory == MOVIES) {
            viewModel.setFavoriteMovies()
        } else if (filmCategory == TV_SHOW) {
            viewModel.setFavoriteTvShow()
        }
    }

    private fun setFavoriteState(state: Boolean) {
        val addFav = detailBinding.iconAddFavorite
        if (state) {
            addFav.setImageResource(R.drawable.ic_favorite)
        } else {
            addFav.setImageResource(R.drawable.ic_favorite_border)
        }
    }

    @JvmName("populateFilmDetailForMovies")
    private fun populateFilmDetail(moviesEntity: MoviesEntity) {
        val genre = moviesEntity.genres.replace("[", "").replace("]", "")

        detailBinding.tvDuratingEps.text = moviesEntity.runtime.toString()
        detailBinding.tvOverview.text = moviesEntity.overview
        detailBinding.tvRating.text = moviesEntity.voteAverage.toString()
        detailBinding.tvGenre.text = genre
        detailBinding.tvReleaseDate.text = moviesEntity.releaseDate
        detailBinding.tvTitle.text = moviesEntity.title

        Glide.with(this)
            .load(BASE_URL_IMAGE + moviesEntity.posterPath)
            .into(detailBinding.imgPoster)

        detailBinding.progressBar.visibility = View.INVISIBLE
    }

    @JvmName("populateFilmDetailForTvShow")
    private fun populateFilmDetail(tvShowEntity: TvShowEntity) {
        val genre = tvShowEntity.genres.replace("[", "").replace("]", "")

        detailBinding.tvDuratingEps.text = tvShowEntity.runtime.toString()
        detailBinding.tvOverview.text = tvShowEntity.overview
        detailBinding.tvRating.text = tvShowEntity.voteAverage.toString()
        detailBinding.tvGenre.text = genre
        detailBinding.tvReleaseDate.text = tvShowEntity.releaseDate
        detailBinding.tvTitle.text = tvShowEntity.name

        Glide.with(this)
            .load(BASE_URL_IMAGE + tvShowEntity.posterPath)
            .into(detailBinding.imgPoster)

        detailBinding.progressBar.visibility = View.INVISIBLE
    }

    private fun setState() {
        if (filmCategory == MOVIES) {
            viewModel.getDetailMovies().observe(this, { movies ->
                when (movies.status) {
                    Status.LOADING -> detailBinding.progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        if (movies.data != null) {
                            detailBinding.progressBar.visibility = View.INVISIBLE
                            val stateFav = movies.data.favorite
                            setFavoriteState(stateFav)
                        }
                    }
                    Status.ERROR -> {
                        detailBinding.progressBar.visibility = View.INVISIBLE
                        Toast.makeText(applicationContext, "Error to Load Movies", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        } else if (filmCategory == TV_SHOW) {
            viewModel.getDetailTvShow().observe(this, { tvShow ->
                when (tvShow.status) {
                    Status.LOADING -> detailBinding.progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        if (tvShow.data != null) {
                            detailBinding.progressBar.visibility = View.INVISIBLE
                            val stateFav = tvShow.data.favorite
                            setFavoriteState(stateFav)
                        }
                    }
                    Status.ERROR -> {
                        detailBinding.progressBar.visibility = View.INVISIBLE
                        Toast.makeText(applicationContext, "Error to Load TV Show", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }


}