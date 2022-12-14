package com.arch3rtemp.android.moviesapp.data.repository

import android.util.Log
import com.arch3rtemp.android.moviesapp.data.global.dto.CastDto
import com.arch3rtemp.android.moviesapp.data.global.dto.CommentDto
import com.arch3rtemp.android.moviesapp.data.global.dto.MovieDto
import com.arch3rtemp.android.moviesapp.data.global.source.CommentRemoteDataSource
import com.arch3rtemp.android.moviesapp.data.global.source.MovieRemoteDataSource
import com.arch3rtemp.android.moviesapp.data.local.entity.CastEntity
import com.arch3rtemp.android.moviesapp.data.local.entity.CommentEntity
import com.arch3rtemp.android.moviesapp.data.local.entity.MovieEntity
import com.arch3rtemp.android.moviesapp.data.local.source.CommentLocalDataSource
import com.arch3rtemp.android.moviesapp.data.local.source.LoginLocalDataSource
import com.arch3rtemp.android.moviesapp.data.local.source.MovieLocalDataSource
import com.arch3rtemp.android.moviesapp.domain.model.Cast
import com.arch3rtemp.android.moviesapp.domain.model.Comment
import com.arch3rtemp.android.moviesapp.domain.model.Movie
import com.arch3rtemp.android.moviesapp.domain.repository.MovieRepository
import com.arch3rtemp.android.moviesapp.util.Mapper
import com.arch3rtemp.android.moviesapp.util.Resource
import com.arch3rtemp.android.moviesapp.util.UiText
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieDtoDomainMapper: Mapper<MovieDto, Movie>,
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieEntityDomainMapper: Mapper<MovieEntity, Movie>,
    private val castDtoDomainMapper: Mapper<CastDto, Cast>,
    private val castEntityDomainMapper: Mapper<CastEntity, Cast>,
    private val commentRemoteDataSource: CommentRemoteDataSource,
    private val commentDtoDomainMapper: Mapper<CommentDto, Comment>,
    private val commentLocalDataSource: CommentLocalDataSource,
    private val commentEntityDomainMapper: Mapper<CommentEntity, Comment>,
    private val loginLocalDataSource: LoginLocalDataSource
) : MovieRepository {

    override fun cacheMovies() = flow {
        emit(Resource.Loading)
        try {
            val tokenObj = loginLocalDataSource.loadToken()
            val token = "${tokenObj.tokenType.replaceFirstChar { it.uppercaseChar() }} ${tokenObj.accessToken}"
            val movies = movieDtoDomainMapper.fromList(movieRemoteDataSource.fetchMovies(token))
            movieLocalDataSource.deleteMovies()
            emit(Resource.Success(movieLocalDataSource.saveMovies(movieEntityDomainMapper.toList(movies))))
        } catch (exception: Exception) {
            emit(Resource.Error(UiText.DynamicString(exception.message.toString())))
        }
    }

    override fun cacheCast(id: Long) = flow {
        emit(Resource.Loading)
        try {
            val tokenObj = loginLocalDataSource.loadToken()
            val token = "${tokenObj.tokenType.replaceFirstChar { it.uppercaseChar() }} ${tokenObj.accessToken}"
            val cast = Cast(id = id, cast = castDtoDomainMapper.from(movieRemoteDataSource.fetchCast(token, id)).cast)
            emit(Resource.Success(movieLocalDataSource.saveCast(castEntityDomainMapper.to(cast))))
        } catch (exception: Exception) {
            emit(Resource.Error(UiText.DynamicString(exception.message.toString())))
        }
    }

    override fun cacheComments(id: String) = flow {
        emit(Resource.Loading)
        try {
            val tokenObj = loginLocalDataSource.loadToken()
            val token = "${tokenObj.tokenType.replaceFirstChar { it.uppercaseChar() }} ${tokenObj.accessToken}"
            val comments = commentDtoDomainMapper.fromList(commentRemoteDataSource.fetchComments(token, id))
            commentLocalDataSource.deleteComments()
            if (comments.isEmpty()) emit(Resource.Empty)
            else emit(Resource.Success(commentLocalDataSource.saveComments(commentEntityDomainMapper.toList(comments))))

        } catch (exception: Exception) {
            emit(Resource.Error(UiText.DynamicString(exception.message.toString())))
        }
    }

    override fun loadMovies() = flow {
        emit(Resource.Loading)
        try {
            emit(Resource.Success(movieEntityDomainMapper.fromList(movieLocalDataSource.loadMovies())))
        } catch (exception: Exception) {
            emit(Resource.Error(UiText.DynamicString(exception.message.toString())))
        }
    }

    override fun loadMovie(id: Long) = flow {
        emit(Resource.Loading)
        try {
            emit(Resource.Success(movieEntityDomainMapper.from(movieLocalDataSource.loadMovie(id))))
        } catch (exception: Exception) {
            emit(Resource.Error(UiText.DynamicString(exception.message.toString())))
        }
    }

    override fun loadComments(id: String) = flow {
        emit(Resource.Loading)
        try {
            val comments = commentEntityDomainMapper.fromList(commentLocalDataSource.loadComments(id))
            if (comments.isEmpty()) {
                emit(Resource.Empty)
            }
            else {
                emit(Resource.Success(comments))
            }
        } catch (exception: Exception) {
            emit(Resource.Error(UiText.DynamicString(exception.message.toString())))
        }
    }

    override fun postComment(comment: Comment) = flow {
        emit(Resource.Loading)
        try {
            val tokenObj = loginLocalDataSource.loadToken()
            val token = "${tokenObj.tokenType.replaceFirstChar { it.uppercaseChar() }} ${tokenObj.accessToken}"
            val commentResponse = commentDtoDomainMapper.from(commentRemoteDataSource.postComment(token, commentDtoDomainMapper.to(comment)))
            commentLocalDataSource.saveComment(commentEntityDomainMapper.to(commentResponse))
            emit(Resource.Success(commentResponse))
        } catch (exception: Exception) {
            emit(Resource.Error(UiText.DynamicString(exception.message.toString())))
        }
    }
}