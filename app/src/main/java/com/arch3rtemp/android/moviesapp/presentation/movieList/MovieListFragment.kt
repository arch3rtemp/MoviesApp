package com.arch3rtemp.android.moviesapp.presentation.movieList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.arch3rtemp.android.moviesapp.R
import com.arch3rtemp.android.moviesapp.databinding.FragmentMovieListBinding
import com.arch3rtemp.android.moviesapp.presentation.base.BaseFragment
import com.arch3rtemp.android.moviesapp.util.Constants.STATUS_ERROR
import com.arch3rtemp.android.moviesapp.util.MultiStateView
import com.arch3rtemp.android.moviesapp.util.showSnackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : BaseFragment<MovieListContract.Event, MovieListContract.State, MovieListContract.Effect, FragmentMovieListBinding, MovieListViewModel>() {

    override val viewModel by viewModels<MovieListViewModel>()
    private var movieListAdapter: MovieListAdapter? = null

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMovieListBinding
        get() = FragmentMovieListBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) {
        initFirstState()
        setupRecyclerView()
        setListeners()
    }

    private fun initFirstState() {
        if (viewModel.currentState.movieListState == MovieListContract.MovieListState.Idle) {
            viewModel.setEvent(MovieListContract.Event.OnGetMovies)
        }
    }

    private fun setupRecyclerView() = with(binding) {
        movieListAdapter = MovieListAdapter {
            findNavController().navigate(MovieListFragmentDirections.actionListFragmentToDetailFragment(
                it.id
            ))
        }
        rvMovieList.adapter = movieListAdapter
    }

    private fun setListeners() = with(binding) {
        multiStateView.getView(MultiStateView.ViewState.EMPTY)
            ?.findViewById<Button>(R.id.retry)
            ?.setOnClickListener {
                viewModel.setEvent(MovieListContract.Event.OnGetMovies)
            }
        multiStateView.getView(MultiStateView.ViewState.ERROR)
            ?.findViewById<Button>(R.id.retry)
            ?.setOnClickListener {
                viewModel.setEvent(MovieListContract.Event.OnGetMovies)
            }
    }

    override fun renderState(state: MovieListContract.State) = with(binding) {
        when(state.movieListState) {
            MovieListContract.MovieListState.Idle -> Unit
            MovieListContract.MovieListState.Loading -> {
                multiStateView.viewState = MultiStateView.ViewState.LOADING
            }
            MovieListContract.MovieListState.Empty -> {
                multiStateView.viewState = MultiStateView.ViewState.EMPTY
            }
            is MovieListContract.MovieListState.Error -> {
                multiStateView.viewState = MultiStateView.ViewState.ERROR
            }
            is MovieListContract.MovieListState.Success -> {
                movieListAdapter?.submitList(state.movieListState.movies)
                multiStateView.viewState = MultiStateView.ViewState.CONTENT
            }
        }
    }

    override fun renderEffect(effect: MovieListContract.Effect) {
        when(effect) {
            is MovieListContract.Effect.ShowSnackBar -> showSnackbar(effect.message.asString(requireContext()), STATUS_ERROR)
        }
    }

}