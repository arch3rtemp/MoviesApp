package com.arch3rtemp.android.moviesapp.data.global.source

import com.arch3rtemp.android.moviesapp.data.global.api.MovieService
import com.arch3rtemp.android.moviesapp.data.global.dto.CastDto
import com.arch3rtemp.android.moviesapp.data.global.dto.CommentDto
import com.arch3rtemp.android.moviesapp.data.global.dto.MovieDto
import com.arch3rtemp.android.moviesapp.domain.model.AuthToken
import com.arch3rtemp.android.moviesapp.domain.model.Comment
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(
    private val movieService: MovieService
) : MovieRemoteDataSource {

    override suspend fun fetchMovies(token: String): List<MovieDto> {
        return movieService.fetchMovies(token)
    }

    override suspend fun fetchCast(token: String, id: Long): CastDto {
        val cast = movieService.fetchCast(token, id)
        return CastDto(cast)
    }

}