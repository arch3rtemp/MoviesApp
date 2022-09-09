package com.arch3rtemp.android.moviesapp.presentation.movieDetail

import androidx.lifecycle.viewModelScope
import com.arch3rtemp.android.moviesapp.domain.usecase.movie.CacheCastUseCase
import com.arch3rtemp.android.moviesapp.domain.usecase.movie.LoadMovieUseCase
import com.arch3rtemp.android.moviesapp.presentation.base.BaseViewModel
import com.arch3rtemp.android.moviesapp.presentation.movieList.MovieListContract
import com.arch3rtemp.android.moviesapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val cacheCastUseCase: CacheCastUseCase,
    private val loadMovieUseCase: LoadMovieUseCase
) : BaseViewModel<MovieDetailContract.Event, MovieDetailContract.State, MovieDetailContract.Effect>() {

    override fun createInitialState(): MovieDetailContract.State {
        return MovieDetailContract.State(movieDetailState = MovieDetailContract.MovieDetailState.Idle)
    }

    override fun handleEvent(event: MovieDetailContract.Event) {
        when(event) {
            is MovieDetailContract.Event.OnGetCast -> cacheCast(event.id)
        }
    }

    private fun cacheCast(id: Long) {
        viewModelScope.launch {
            cacheCastUseCase(id)
                .collectLatest {
                    when(it) {
                        Resource.Loading -> setState { copy(movieDetailState = MovieDetailContract.MovieDetailState.Loading) }
                        Resource.Empty -> Unit
                        is Resource.Error -> {
                            setState { copy(movieDetailState = MovieDetailContract.MovieDetailState.Error) }
                            setEffect { MovieDetailContract.Effect.ShowSnackbar(it.message) }
                        }
                        is Resource.Success -> getMovie(id)
                    }

                }
        }
    }

    private fun getMovie(id: Long) {
        viewModelScope.launch {
            loadMovieUseCase(id)
                .collectLatest {
                    when(it) {
                        Resource.Loading -> setState { copy(movieDetailState = MovieDetailContract.MovieDetailState.Loading) }
                        Resource.Empty -> Unit
                        is Resource.Error -> {
                            setState { copy(movieDetailState = MovieDetailContract.MovieDetailState.Error) }
                            setEffect { MovieDetailContract.Effect.ShowSnackbar(it.message) }
                        }
                        is Resource.Success -> setState { copy(movieDetailState = MovieDetailContract.MovieDetailState.Success, movie = it.data) }
                    }
                }
        }
    }
}