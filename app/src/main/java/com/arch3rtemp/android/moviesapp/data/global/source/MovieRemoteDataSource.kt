package com.arch3rtemp.android.moviesapp.data.global.source

import com.arch3rtemp.android.moviesapp.data.global.dto.CastDto
import com.arch3rtemp.android.moviesapp.data.global.dto.MovieDto
import com.arch3rtemp.android.moviesapp.domain.model.Comment

interface MovieRemoteDataSource {

    suspend fun fetchMovies(): List<MovieDto>

    suspend fun fetchCast(id: String): CastDto

    suspend fun fetchComments(id: String): List<Comment>

    suspend fun postComment(comment: Comment)

}