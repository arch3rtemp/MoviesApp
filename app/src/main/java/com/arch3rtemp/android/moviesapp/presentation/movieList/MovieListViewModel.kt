package com.arch3rtemp.android.moviesapp.presentation.movieList

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.arch3rtemp.android.moviesapp.domain.usecase.movie.CacheMoviesUseCase
import com.arch3rtemp.android.moviesapp.domain.usecase.movie.LoadMoviesUseCase
import com.arch3rtemp.android.moviesapp.presentation.base.BaseViewModel
import com.arch3rtemp.android.moviesapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val cacheMoviesUseCase: CacheMoviesUseCase,
    private val loadMoviesUseCase: LoadMoviesUseCase
) : BaseViewModel<MovieListContract.Event, MovieListContract.State, MovieListContract.Effect>() {

    override fun createInitialState(): MovieListContract.State {
        return MovieListContract.State(movieListState = MovieListContract.MovieListState.Idle)
    }

    override fun handleEvent(event: MovieListContract.Event) {
        when(event) {
            MovieListContract.Event.OnGetMovies -> cacheMovies()
        }
    }

    private fun cacheMovies() {
        viewModelScope.launch {
            cacheMoviesUseCase()
                .collectLatest {
                    when(it) {
                        Resource.Loading -> setState { copy(movieListState = MovieListContract.MovieListState.Loading) }
                        Resource.Empty -> setState { copy(movieListState = MovieListContract.MovieListState.Empty) }
                        is Resource.Error -> {
                            setState { copy(movieListState = MovieListContract.MovieListState.Error(message = it.message)) }
                            setEffect { MovieListContract.Effect.ShowSnackBar(it.message) }
                            loadMovies()
                        }
                        is Resource.Success -> loadMovies()
                    }
                }
        }
    }

    private fun loadMovies() {
        viewModelScope.launch {
            loadMoviesUseCase()
                .collectLatest {
                    when(it) {
                        Resource.Loading -> setState { copy(movieListState = MovieListContract.MovieListState.Loading) }
                        Resource.Empty -> setState { copy(movieListState = MovieListContract.MovieListState.Empty) }
                        is Resource.Error -> setState { copy(movieListState = MovieListContract.MovieListState.Error(it.message)) }
                        is Resource.Success -> setState { copy(movieListState = MovieListContract.MovieListState.Success(it.data)) }
                    }
                }
        }
    }
}