package com.arch3rtemp.android.moviesapp.presentation.movieList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import coil.load
import com.arch3rtemp.android.moviesapp.databinding.RecyclerMovieItemBinding
import com.arch3rtemp.android.moviesapp.domain.model.Movie
import com.arch3rtemp.android.moviesapp.presentation.base.BaseAdapter
import com.arch3rtemp.android.moviesapp.presentation.base.BaseViewHolder

class MovieListAdapter constructor(
    private val onItemClicked: (Movie) -> Unit
) : BaseAdapter<Movie, RecyclerMovieItemBinding, MovieListAdapter.MovieViewHolder>(MovieDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val viewHolder = MovieViewHolder(
            RecyclerMovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
        viewHolder.itemView.setOnClickListener {
            viewHolder.getRowItem()?.let {
                onItemClicked(it)
            }
        }
        return viewHolder
    }

    class MovieViewHolder(private val binding: RecyclerMovieItemBinding) :
        BaseViewHolder<Movie, RecyclerMovieItemBinding>(binding) {
        override fun bind() {
            getRowItem()?.let {
                binding.apply {
                    ivPoster.load(it.posterUrl)
                    tvTitle.text = it.title
                    tvYear.text = it.year
                    tvDuration.text = it.duration
                    tvRating.text = it.rating
                }
            }
        }
    }

    object MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
}