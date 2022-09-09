package com.arch3rtemp.android.moviesapp.data.global.source

import com.arch3rtemp.android.moviesapp.data.global.api.CommentService
import com.arch3rtemp.android.moviesapp.data.global.dto.CommentDto
import com.arch3rtemp.android.moviesapp.domain.model.AuthToken
import javax.inject.Inject

class CommentRemoteDataSourceImpl @Inject constructor(
    private val commentService: CommentService
) : CommentRemoteDataSource {

    override suspend fun fetchComments(token: String, id: String): List<CommentDto> {
        return commentService.fetchComments(token, id)
    }

    override suspend fun postComment(token: String, comment: CommentDto): CommentDto {
        return commentService.postComment(token, comment.movieId!!, comment)
    }
}