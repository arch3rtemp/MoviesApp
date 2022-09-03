package com.arch3rtemp.android.moviesapp.data.local.source

import com.arch3rtemp.android.moviesapp.data.local.entity.CastEntity
import com.arch3rtemp.android.moviesapp.data.local.entity.MovieEntity

interface MovieLocalDataSource {
    suspend fun saveMovies(movies: List<MovieEntity>)
    suspend fun saveCast(cast: CastEntity)
    fun loadMovies(): List<MovieEntity>
    fun loadMovie(id: Long): MovieEntity
    suspend fun deleteMovies()
}