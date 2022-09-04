package com.arch3rtemp.android.moviesapp.domain.usecase.movie

import com.arch3rtemp.android.moviesapp.domain.model.Comment
import com.arch3rtemp.android.moviesapp.domain.repository.MovieRepository
import com.arch3rtemp.android.moviesapp.util.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class LoadCommentsUseCase(
    private val movieRepository: MovieRepository,
    private val dispatcher: CoroutineDispatcher
) {
    operator fun invoke(id: String): Flow<Resource<List<Comment>>> {
        return movieRepository.loadComments(id).flowOn(dispatcher)
    }
}