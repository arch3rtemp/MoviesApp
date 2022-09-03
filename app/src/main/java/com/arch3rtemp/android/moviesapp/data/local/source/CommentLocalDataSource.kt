package com.arch3rtemp.android.moviesapp.data.local.source

import com.arch3rtemp.android.moviesapp.data.local.entity.CommentEntity

interface CommentLocalDataSource {
    suspend fun saveComments(comments: List<CommentEntity>)
    fun loadComments(id: String): List<CommentEntity>
}