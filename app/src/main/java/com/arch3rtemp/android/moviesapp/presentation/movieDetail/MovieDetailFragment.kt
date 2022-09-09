package com.arch3rtemp.android.moviesapp.presentation.movieDetail

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.arch3rtemp.android.moviesapp.databinding.FragmentDetailBinding
import com.arch3rtemp.android.moviesapp.domain.model.Movie
import com.arch3rtemp.android.moviesapp.presentation.base.BaseFragment
import com.arch3rtemp.android.moviesapp.util.Constants
import com.arch3rtemp.android.moviesapp.util.showSnackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : BaseFragment<MovieDetailContract.Event, MovieDetailContract.State, MovieDetailContract.Effect, FragmentDetailBinding, MovieDetailViewModel>() {

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailBinding
        get() = FragmentDetailBinding::inflate

    override val viewModel by viewModels<MovieDetailViewModel>()
    private val arg by navArgs<MovieDetailFragmentArgs>()

    override fun prepareView(savedInstanceState: Bundle?) {
        initFirstState()
        setListeners()
    }

    private fun initFirstState() {
        if (viewModel.currentState.movieDetailState == MovieDetailContract.MovieDetailState.Idle) {
            viewModel.setEvent(MovieDetailContract.Event.OnGetCast(arg.id))
        }
    }

    private fun setListeners() = with(binding) {
        btnComments.setOnClickListener {
            findNavController().navigate(MovieDetailFragmentDirections.actionDetailFragmentToCommentFragment(arg.id.toString()))
        }
    }

    override fun renderState(state: MovieDetailContract.State) {
        when(state.movieDetailState) {
            MovieDetailContract.MovieDetailState.Idle -> hideProgressDialog()
            MovieDetailContract.MovieDetailState.Loading -> showProgressDialog()
            MovieDetailContract.MovieDetailState.Empty -> hideProgressDialog()
            MovieDetailContract.MovieDetailState.Error -> hideProgressDialog()
            MovieDetailContract.MovieDetailState.Success -> {
                hideProgressDialog()
                setData(state.movie)
            }
        }
    }

    private fun setData(movie: Movie) = with(binding) {
        ivPoster.load(movie.posterUrl)
        tvTitle.text = movie.title
        tvYear.text = movie.year
        tvDuration.text = movie.duration
        tvRating.text = movie.rating
        tvCast.text = TextUtils.join(", ", movie.cast)
    }

    override fun renderEffect(effect: MovieDetailContract.Effect) {
        when(effect) {
            is MovieDetailContract.Effect.ShowSnackbar -> showSnackbar(effect.message.asString(requireContext()), Constants.STATUS_ERROR)
        }
    }
}