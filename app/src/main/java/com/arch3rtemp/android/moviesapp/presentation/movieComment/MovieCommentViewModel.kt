package com.arch3rtemp.android.moviesapp.presentation.movieComment

import androidx.lifecycle.viewModelScope
import com.arch3rtemp.android.moviesapp.domain.model.Comment
import com.arch3rtemp.android.moviesapp.domain.usecase.movie.CacheCommentsUseCase
import com.arch3rtemp.android.moviesapp.domain.usecase.movie.LoadCommentsUseCase
import com.arch3rtemp.android.moviesapp.domain.usecase.movie.PostCommentUseCase
import com.arch3rtemp.android.moviesapp.presentation.base.BaseViewModel
import com.arch3rtemp.android.moviesapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieCommentViewModel @Inject constructor(
    private val cacheCommentsUseCase: CacheCommentsUseCase,
    private val loadCommentsUseCase: LoadCommentsUseCase,
    private val postCommentUseCase: PostCommentUseCase
) : BaseViewModel<MovieCommentContract.Event, MovieCommentContract.State, MovieCommentContract.Effect>() {

    override fun createInitialState(): MovieCommentContract.State {
        return MovieCommentContract.State(
            commentsState = MovieCommentContract.CommentsState(MovieCommentContract.MovieCommentState.Idle),
            postCommentState = MovieCommentContract.PostCommentState(MovieCommentContract.MovieCommentState.Idle)
        )
    }

    override fun handleEvent(event: MovieCommentContract.Event) {
        when (event) {
            is MovieCommentContract.Event.OnGetComments -> cacheComments(event.id)
            is MovieCommentContract.Event.OnPostComment -> postComment(event.comment)
        }
    }

    private fun cacheComments(id: String) {
        viewModelScope.launch {
            cacheCommentsUseCase(id)
                .collectLatest {
                    when (it) {
                        Resource.Loading -> setState { copy(commentsState = MovieCommentContract.CommentsState(MovieCommentContract.MovieCommentState.Loading)) }
                        Resource.Empty -> setState { copy(commentsState = MovieCommentContract.CommentsState(MovieCommentContract.MovieCommentState.Empty)) }
                        is Resource.Error -> {
                            setState { copy(commentsState = MovieCommentContract.CommentsState(MovieCommentContract.MovieCommentState.Error)) }
                            setEffect { MovieCommentContract.Effect.ShowSnackBar(it.message) }
                        }
                        is Resource.Success -> loadComments(id)
                    }
                }
        }
    }

    private fun loadComments(id: String) {
        viewModelScope.launch {
            loadCommentsUseCase(id)
                .collectLatest {
                    when (it) {
                        Resource.Loading -> setState { copy(commentsState = MovieCommentContract.CommentsState(MovieCommentContract.MovieCommentState.Loading)) }
                        Resource.Empty -> setState { copy(commentsState = MovieCommentContract.CommentsState(MovieCommentContract.MovieCommentState.Empty)) }
                        is Resource.Error -> {
                            setState { copy(commentsState = MovieCommentContract.CommentsState(MovieCommentContract.MovieCommentState.Error)) }
                            setEffect { MovieCommentContract.Effect.ShowSnackBar(it.message) }
                        }
                        is Resource.Success -> {
                            setState { copy(commentsState = MovieCommentContract.CommentsState(MovieCommentContract.MovieCommentState.Success, comments = it.data)) }
                        }
                    }
                }
        }
    }

    private fun postComment(comment: Comment) {
        viewModelScope.launch {
            postCommentUseCase(comment)
                .collectLatest {
                    when (it) {
                        Resource.Loading -> setState { copy(postCommentState = MovieCommentContract.PostCommentState(MovieCommentContract.MovieCommentState.Loading)) }
                        Resource.Empty -> Unit
                        is Resource.Error -> setEffect { MovieCommentContract.Effect.ShowSnackBar(it.message) }
                        is Resource.Success -> setState { copy(postCommentState = MovieCommentContract.PostCommentState(MovieCommentContract.MovieCommentState.Success, comment = it.data)) }
                    }
                }
        }
    }
}