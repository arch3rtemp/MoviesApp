package com.arch3rtemp.android.moviesapp.data.global.api

import com.arch3rtemp.android.moviesapp.data.global.dto.CastDto
import com.arch3rtemp.android.moviesapp.data.global.dto.CommentDto
import com.arch3rtemp.android.moviesapp.data.global.dto.MovieDto
import com.arch3rtemp.android.moviesapp.domain.model.AuthToken
import com.arch3rtemp.android.moviesapp.domain.model.Comment
import retrofit2.http.*

interface MovieService {

    @GET("Movies")
    suspend fun fetchMovies(@Header("Authorization") token: String): List<MovieDto>

    @GET("Movies/{id}/Cast")
    suspend fun fetchCast(@Header("Authorization") token: String, @Path("id") id: Long): CastDto

}