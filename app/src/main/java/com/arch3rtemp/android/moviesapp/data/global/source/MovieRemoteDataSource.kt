package com.arch3rtemp.android.moviesapp.data.global.source

import com.arch3rtemp.android.moviesapp.data.global.dto.CastDto
import com.arch3rtemp.android.moviesapp.data.global.dto.MovieDto
import com.arch3rtemp.android.moviesapp.domain.model.AuthToken

interface MovieRemoteDataSource {

    suspend fun fetchMovies(token: String): List<MovieDto>

    suspend fun fetchCast(token: String, id: Long): CastDto

}