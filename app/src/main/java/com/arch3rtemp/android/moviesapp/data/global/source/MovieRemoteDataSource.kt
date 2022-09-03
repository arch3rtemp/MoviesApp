package com.arch3rtemp.android.moviesapp.data.global.source

import com.arch3rtemp.android.moviesapp.data.global.dto.CastDto
import com.arch3rtemp.android.moviesapp.data.global.dto.MovieDto

interface MovieRemoteDataSource {

    suspend fun fetchMovies(): List<MovieDto>

    suspend fun fetchCast(id: Long): CastDto

}