package com.arch3rtemp.android.moviesapp.presentation.movieComment

import com.arch3rtemp.android.moviesapp.domain.model.Comment
import com.arch3rtemp.android.moviesapp.util.UiEffect
import com.arch3rtemp.android.moviesapp.util.UiEvent
import com.arch3rtemp.android.moviesapp.util.UiState
import com.arch3rtemp.android.moviesapp.util.UiText

class MovieCommentContract {
    sealed class MovieCommentState {
        object Idle : MovieCommentState()
        object Loading : MovieCommentState()
        object Empty : MovieCommentState()
        object Error : MovieCommentState()
        object Success : MovieCommentState()
    }

    sealed class Event : UiEvent {
        data class OnGetComments(val id: String) : Event()
        data class OnPostComment(val comment: Comment) : Event()
    }

    sealed class Effect : UiEffect {
        data class ShowSnackBar(val message: UiText) : Effect()
    }

    data class State(
        val commentsState: CommentsState,
        val postCommentState: PostCommentState
        ) : UiState

    data class CommentsState(
        val commentListState: MovieCommentState,
        val comments: List<Comment> = listOf()
    )

    data class PostCommentState(
        val postCommentState: MovieCommentState,
        val comment: Comment = Comment()
    )
}