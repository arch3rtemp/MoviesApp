package com.arch3rtemp.android.moviesapp.data.global.api

import com.arch3rtemp.android.moviesapp.data.global.dto.CommentDto
import retrofit2.http.*

interface CommentService {

    @GET("Movies/{id}/Comments")
    suspend fun fetchComments(@Path("id") id: String): List<CommentDto>

    @POST("Movies/{id}/Comments/Post")
    suspend fun postComment(@Path("id") id: String, @Body comment: CommentDto)

}