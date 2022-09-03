package com.arch3rtemp.android.moviesapp.data.local.source

import com.arch3rtemp.android.moviesapp.domain.model.Comment
import com.arch3rtemp.android.moviesapp.domain.model.Movie

class MovieLocalDataSourceImpl : MovieLocalDataSource {
    override suspend fun saveMovies(movies: List<Movie>) {
        TODO("Not yet implemented")
    }

    override fun loadMovies(): List<Movie> {
        TODO("Not yet implemented")
    }

    override fun loadMovie(id: String): Movie {
        TODO("Not yet implemented")
    }

    override suspend fun deleteMovies() {
        TODO("Not yet implemented")
    }

    override fun loadComments(id: String): List<Comment> {
        TODO("Not yet implemented")
    }
}