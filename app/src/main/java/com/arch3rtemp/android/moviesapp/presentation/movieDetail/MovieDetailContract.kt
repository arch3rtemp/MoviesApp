package com.arch3rtemp.android.moviesapp.presentation.movieDetail

import com.arch3rtemp.android.moviesapp.domain.model.Movie
import com.arch3rtemp.android.moviesapp.util.UiEffect
import com.arch3rtemp.android.moviesapp.util.UiEvent
import com.arch3rtemp.android.moviesapp.util.UiState
import com.arch3rtemp.android.moviesapp.util.UiText

class MovieDetailContract {
    sealed class MovieDetailState {
        object Idle : MovieDetailState()
        object Loading : MovieDetailState()
        object Empty : MovieDetailState()
        object Error : MovieDetailState()
        object Success : MovieDetailState()
    }

    sealed class Event: UiEvent {
        data class OnGetCast(val id: Long) : Event()
    }

    sealed class Effect : UiEffect {
        data class ShowSnackbar(val message: UiText) : Effect()
    }

    data class State(
        val movie: Movie = Movie(),
        val movieDetailState: MovieDetailState
    ) : UiState
}