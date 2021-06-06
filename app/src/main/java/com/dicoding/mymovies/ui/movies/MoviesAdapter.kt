package com.dicoding.mymovies.ui.movies

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.mymovies.R
import com.dicoding.mymovies.data.source.local.entity.MoviesEntity
import com.dicoding.mymovies.databinding.ItemsMoviesBinding
import com.dicoding.mymovies.ui.detail.DetailMoviesActivity
import com.dicoding.mymovies.ui.detail.DetailMoviesViewModel.Companion.MOVIES
import com.dicoding.mymovies.utils.ConstantValue.BASE_URL_IMAGE

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    private val listMovies = ArrayList<MoviesEntity>()
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(id: String)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setMovies(movies: List<MoviesEntity>?) {
        if (movies == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesViewHolder {
        val itemsMoviesBinding = ItemsMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(itemsMoviesBinding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movies = listMovies[position]
        holder.bind(movies)
    }

    override fun getItemCount(): Int = listMovies.size

    inner class MoviesViewHolder(private val binding: ItemsMoviesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movies: MoviesEntity) {
            with(binding) {
                tvItemTitle.text = movies.title
                tvItemGenre.text = movies.voteAverage.toString()

                Glide.with(itemView.context)
                    .load(BASE_URL_IMAGE + movies.posterPath)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(imgPoster)

                itemView.setOnClickListener {
                    onItemClickCallback.onItemClicked(movies.id.toString())
                }

                /*itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailMoviesActivity::class.java)
                    intent.putExtra(DetailMoviesActivity.EXTRA_FILM, movies.id)
                    intent.putExtra(DetailMoviesActivity.EXTRA_CATEGORY, MOVIES)
                    itemView.context.startActivity(intent)
                }*/
            }
        }
    }
}