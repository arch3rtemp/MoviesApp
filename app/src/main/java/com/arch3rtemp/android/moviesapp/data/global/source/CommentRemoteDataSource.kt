package com.arch3rtemp.android.moviesapp.data.global.source

import com.arch3rtemp.android.moviesapp.data.global.dto.CommentDto

interface CommentRemoteDataSource {

    suspend fun fetchComments(id: String): List<CommentDto>

    suspend fun postComment(comment: CommentDto)

}