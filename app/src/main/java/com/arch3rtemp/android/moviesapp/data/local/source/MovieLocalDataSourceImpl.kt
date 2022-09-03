package com.arch3rtemp.android.moviesapp.data.local.source

import com.arch3rtemp.android.moviesapp.data.local.entity.MovieEntity

class MovieLocalDataSourceImpl : MovieLocalDataSource {
    override suspend fun saveMovies(movies: List<MovieEntity>) {
        TODO("Not yet implemented")
    }

    override suspend fun saveCast(id: Long, cast: List<String>) {
        TODO("Not yet implemented")
    }

    override fun loadMovies(): List<MovieEntity> {
        TODO("Not yet implemented")
    }

    override fun loadMovie(id: Long): MovieEntity {
        TODO("Not yet implemented")
    }

    override suspend fun deleteMovies() {
        TODO("Not yet implemented")
    }
}