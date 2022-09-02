package com.arch3rtemp.android.moviesapp.data.global.api

import com.arch3rtemp.android.moviesapp.data.global.dto.CastDto
import com.arch3rtemp.android.moviesapp.data.global.dto.MovieDto
import com.arch3rtemp.android.moviesapp.domain.model.Comment
import retrofit2.http.*

interface MovieService {

    @GET("Movies")
    suspend fun fetchMovies(): List<MovieDto>

    @GET("Movies/{id}/Cast")
    suspend fun fetchCast(@Path("id") id: String): CastDto

    @GET("Movies/{id}/Comments")
    suspend fun fetchComments(@Path("id") id: String): List<Comment>

    @POST("Movies/{id}/Comments/Post")
    @FormUrlEncoded
    suspend fun postComment(@Field("Post") comment: Comment)
}