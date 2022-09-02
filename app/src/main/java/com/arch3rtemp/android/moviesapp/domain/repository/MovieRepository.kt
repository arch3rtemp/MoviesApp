package com.arch3rtemp.android.moviesapp.domain.repository

import com.arch3rtemp.android.moviesapp.domain.model.Cast
import com.arch3rtemp.android.moviesapp.domain.model.Comment
import com.arch3rtemp.android.moviesapp.domain.model.Movie
import com.arch3rtemp.android.moviesapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun cacheMovies(): Flow<Resource<Unit>>
    fun cacheCast(id: String): Flow<Resource<Unit>>
    fun cacheComments(id: String): Flow<Resource<Unit>>

    fun loadMovies(): Flow<Resource<List<Movie>>>
    fun loadMovie(id: String): Flow<Resource<Movie>>
    fun loadCast(id: String): Flow<Resource<Cast>>
    fun loadComments(id: String): Flow<Resource<List<Comment>>>

    fun postComment(id: String): Flow<Resource<Comment>>
}