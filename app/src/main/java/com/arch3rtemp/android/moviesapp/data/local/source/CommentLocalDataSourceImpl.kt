package com.arch3rtemp.android.moviesapp.data.local.source

import com.arch3rtemp.android.moviesapp.data.local.dao.CommentDao
import com.arch3rtemp.android.moviesapp.data.local.entity.CommentEntity
import javax.inject.Inject

class CommentLocalDataSourceImpl @Inject constructor(
    private val commentDao: CommentDao
) : CommentLocalDataSource {

    override suspend fun saveComments(comments: List<CommentEntity>) {
        commentDao.insertComments(comments)
    }

    override suspend fun saveComment(comment: CommentEntity) {
        commentDao.insertComment(comment)
    }

    override fun loadComments(id: String): List<CommentEntity> {
        return commentDao.selectComments(id)
    }
}