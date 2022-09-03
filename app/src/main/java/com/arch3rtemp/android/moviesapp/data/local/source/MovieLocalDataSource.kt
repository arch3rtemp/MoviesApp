package com.arch3rtemp.android.moviesapp.data.local.source

import com.arch3rtemp.android.moviesapp.domain.model.Comment
import com.arch3rtemp.android.moviesapp.domain.model.Movie

interface MovieLocalDataSource {
    suspend fun saveMovies(movies: List<Movie>)
    fun loadMovies(): List<Movie>
    fun loadMovie(id: String): Movie
    suspend fun deleteMovies()
    fun loadComments(id: String): List<Comment>
}