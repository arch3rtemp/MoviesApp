package com.arch3rtemp.android.moviesapp.data.global.api

import com.arch3rtemp.android.moviesapp.data.global.dto.CommentDto
import com.arch3rtemp.android.moviesapp.domain.model.AuthToken
import retrofit2.http.*

interface CommentService {

    @GET("Movies/{id}/Comments")
    suspend fun fetchComments(@Header("Authorization") token: String, @Path("id") id: String): List<CommentDto>

    @POST("Movies/{id}/Comments/Post")
    suspend fun postComment(@Header("Authorization") token: String, @Path("id") id: String, @Body comment: CommentDto): CommentDto

}