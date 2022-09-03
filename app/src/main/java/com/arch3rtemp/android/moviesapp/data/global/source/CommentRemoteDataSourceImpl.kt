package com.arch3rtemp.android.moviesapp.data.global.source

import com.arch3rtemp.android.moviesapp.data.global.api.CommentService
import com.arch3rtemp.android.moviesapp.data.global.dto.CommentDto
import javax.inject.Inject

class CommentRemoteDataSourceImpl @Inject constructor(
    private val commentService: CommentService
) : CommentRemoteDataSource {
    override suspend fun fetchComments(id: String): List<CommentDto> {
        return commentService.fetchComments(id)
    }

    override suspend fun postComment(comment: CommentDto) {
        commentService.postComment(comment)
    }
}