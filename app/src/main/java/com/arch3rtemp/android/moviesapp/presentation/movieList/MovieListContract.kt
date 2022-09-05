package com.arch3rtemp.android.moviesapp.presentation.movieList

import com.arch3rtemp.android.moviesapp.domain.model.Movie
import com.arch3rtemp.android.moviesapp.util.UiEffect
import com.arch3rtemp.android.moviesapp.util.UiEvent
import com.arch3rtemp.android.moviesapp.util.UiState
import com.arch3rtemp.android.moviesapp.util.UiText

class MovieListContract {
    sealed class MovieListState {
        object Idle : MovieListState()
        object Loading : MovieListState()
        data class Error(val message: UiText) : MovieListState()
        object Empty : MovieListState()
        data class Success(val movies: List<Movie> = listOf()) : MovieListState()
    }

    sealed class Event : UiEvent {
        object OnGetMovies : Event()
    }

    sealed class Effect : UiEffect {
        data class ShowSnackBar(val message: UiText) : Effect()
    }

    data class State(
        val movieListState: MovieListState
    ) : UiState
}