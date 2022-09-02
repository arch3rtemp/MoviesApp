package com.arch3rtemp.android.moviesapp.data.repository

import com.arch3rtemp.android.moviesapp.domain.model.Cast
import com.arch3rtemp.android.moviesapp.domain.model.Comment
import com.arch3rtemp.android.moviesapp.domain.model.Movie
import com.arch3rtemp.android.moviesapp.domain.repository.MovieRepository
import com.arch3rtemp.android.moviesapp.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(

) : MovieRepository {
    override fun cacheMovies(): Flow<Resource<Unit>> {
        TODO("Not yet implemented")
    }

    override fun cacheCast(id: String): Flow<Resource<Unit>> {
        TODO("Not yet implemented")
    }

    override fun cacheComments(id: String): Flow<Resource<Unit>> {
        TODO("Not yet implemented")
    }

    override fun loadMovies(): Flow<Resource<List<Movie>>> {
        TODO("Not yet implemented")
    }

    override fun loadMovie(id: String): Flow<Resource<Movie>> {
        TODO("Not yet implemented")
    }

    override fun loadCast(id: String): Flow<Resource<Cast>> {
        TODO("Not yet implemented")
    }

    override fun loadComments(id: String): Flow<Resource<List<Comment>>> {
        TODO("Not yet implemented")
    }

    override fun postComment(id: String): Flow<Resource<Comment>> {
        TODO("Not yet implemented")
    }
}