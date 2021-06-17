package com.dicoding.mymovies.ui.favorite.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.mymovies.R
import com.dicoding.mymovies.data.source.local.entity.TvShowEntity
import com.dicoding.mymovies.databinding.ItemsFilmBinding

class TvShowFavoriteAdapter: PagedListAdapter<TvShowEntity, TvShowFavoriteAdapter.TvShowFavoriteViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(id: String)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun getSwipedData(swipedPosition: Int) : TvShowEntity? = getItem(swipedPosition)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TvShowFavoriteAdapter.TvShowFavoriteViewHolder {
        val itemFilmBinding = ItemsFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowFavoriteViewHolder(itemFilmBinding)
    }

    override fun onBindViewHolder(
        holder: TvShowFavoriteAdapter.TvShowFavoriteViewHolder,
        position: Int
    ) {
        val tvShow = getItem(position)
        if (tvShow != null) {
            holder.bind(tvShow)
        }
    }

    inner class TvShowFavoriteViewHolder(private val binding: ItemsFilmBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShowEntity: TvShowEntity) {
            with(binding) {
                tvItemTitle.text = tvShowEntity.name
                tvItemRating.text = tvShowEntity.voteAverage.toString()

                Glide.with(itemView.context)
                    .asBitmap()
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(imgPoster)

                itemView.setOnClickListener {
                    onItemClickCallback.onItemClicked(tvShowEntity.id.toString())
                }
            }
        }
    }

}