package com.arch3rtemp.android.moviesapp.data.local.source

import com.arch3rtemp.android.moviesapp.data.local.dao.MovieDao
import com.arch3rtemp.android.moviesapp.data.local.entity.CastEntity
import com.arch3rtemp.android.moviesapp.data.local.entity.MovieEntity
import javax.inject.Inject

class MovieLocalDataSourceImpl @Inject constructor(
    private val movieDao: MovieDao
) : MovieLocalDataSource {

    override suspend fun saveMovies(movies: List<MovieEntity>) {
        movieDao.insertMovies(movies)
    }

    override suspend fun saveCast(cast: CastEntity) {
        movieDao.insertCast(cast)
    }

    override fun loadMovies(): List<MovieEntity> {
        return movieDao.selectMovies()
    }

    override fun loadMovie(id: Long): MovieEntity {
        return movieDao.selectMovie(id)
    }

    override suspend fun deleteMovies() {
        movieDao.deleteMovies()
    }
}