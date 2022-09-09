package com.arch3rtemp.android.moviesapp.presentation.movieComment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.arch3rtemp.android.moviesapp.databinding.RecyclerCommentItemBinding
import com.arch3rtemp.android.moviesapp.domain.model.Comment
import com.arch3rtemp.android.moviesapp.presentation.base.BaseAdapter
import com.arch3rtemp.android.moviesapp.presentation.base.BaseViewHolder

class MovieCommentAdapter : BaseAdapter<Comment, RecyclerCommentItemBinding, MovieCommentAdapter.CommentViewHolder>(CommentDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        return CommentViewHolder(
            RecyclerCommentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    class CommentViewHolder(private val binding: RecyclerCommentItemBinding) :
            BaseViewHolder<Comment, RecyclerCommentItemBinding>(binding) {
        override fun bind() {
            getRowItem()?.let {
                binding.apply {
                    tvComment.text = it.message
                }
            }
        }
    }

    object CommentDiffCallback : DiffUtil.ItemCallback<Comment>() {
        override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
            return oldItem == newItem
        }
    }
}