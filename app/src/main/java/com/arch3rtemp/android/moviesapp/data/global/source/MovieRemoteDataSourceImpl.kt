package com.arch3rtemp.android.moviesapp.data.global.source

import com.arch3rtemp.android.moviesapp.data.global.api.MovieService
import com.arch3rtemp.android.moviesapp.data.global.dto.CastDto
import com.arch3rtemp.android.moviesapp.data.global.dto.MovieDto
import com.arch3rtemp.android.moviesapp.domain.model.Comment
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(
    private val movieService: MovieService
) : MovieRemoteDataSource {
    override suspend fun fetchMovies(): List<MovieDto> {
        return movieService.fetchMovies()
    }

    override suspend fun fetchCast(id: String): CastDto {
        return movieService.fetchCast(id)
    }

    override suspend fun fetchComments(id: String): List<Comment> {
        return movieService.fetchComments(id)
    }

    override suspend fun postComment(comment: Comment) {
        return movieService.postComment(comment)
    }
}