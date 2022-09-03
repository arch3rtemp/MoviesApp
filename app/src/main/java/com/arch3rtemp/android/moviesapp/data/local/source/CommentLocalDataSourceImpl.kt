package com.arch3rtemp.android.moviesapp.data.local.source

import com.arch3rtemp.android.moviesapp.data.local.entity.CommentEntity

class CommentLocalDataSourceImpl : CommentLocalDataSource {

    override suspend fun saveComments(comments: List<CommentEntity>) {

    }

    override fun loadComments(id: String): List<CommentEntity> {
        TODO("Not yet implemented")
    }
}