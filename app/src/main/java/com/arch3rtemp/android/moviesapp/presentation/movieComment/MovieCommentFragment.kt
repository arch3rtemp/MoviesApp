package com.arch3rtemp.android.moviesapp.presentation.movieComment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.arch3rtemp.android.moviesapp.R
import com.arch3rtemp.android.moviesapp.databinding.FragmentMovieCommentBinding
import com.arch3rtemp.android.moviesapp.domain.model.Comment
import com.arch3rtemp.android.moviesapp.presentation.base.BaseFragment
import com.arch3rtemp.android.moviesapp.util.Constants.STATUS_ERROR
import com.arch3rtemp.android.moviesapp.util.MultiStateView
import com.arch3rtemp.android.moviesapp.util.showSnackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieCommentFragment : BaseFragment<MovieCommentContract.Event, MovieCommentContract.State, MovieCommentContract.Effect, FragmentMovieCommentBinding, MovieCommentViewModel>() {

    override val viewModel by viewModels<MovieCommentViewModel>()
    private val arg by navArgs<MovieCommentFragmentArgs>()
    private var commentAdapter: MovieCommentAdapter? = null

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMovieCommentBinding
        get() = FragmentMovieCommentBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) {
        initFirstState()
        setupRecyclerView()
        setListeners()
    }

    private fun initFirstState() {
        if (viewModel.currentState.state == MovieCommentContract.MovieCommentState.Idle) {
            viewModel.setEvent(MovieCommentContract.Event.OnGetComments(arg.id))
        }
    }

    private fun setupRecyclerView() = with(binding) {
        commentAdapter = MovieCommentAdapter()
        rvComments.adapter = commentAdapter
    }

    private fun setListeners() = with(binding) {
        multiStateView.getView(MultiStateView.ViewState.EMPTY)
            ?.findViewById<Button>(R.id.retry)
            ?.setOnClickListener {
                viewModel.setEvent(MovieCommentContract.Event.OnGetComments(arg.id))
            }
        multiStateView.getView(MultiStateView.ViewState.ERROR)
            ?.findViewById<Button>(R.id.retry)
            ?.setOnClickListener {
                viewModel.setEvent(MovieCommentContract.Event.OnGetComments(arg.id))
            }
        btnSend.setOnClickListener {
            postComment(etComment.text.toString())
        }
    }

    private fun postComment(message: String) = with(binding) {
        val comment = Comment(movieId = arg.id, message = message)
        viewModel.setEvent(MovieCommentContract.Event.OnPostComment(comment))
        etComment.setText("")
        etComment.clearFocus()
    }

    override fun renderState(state: MovieCommentContract.State) = with(binding) {
        when(state.state) {
            MovieCommentContract.MovieCommentState.Idle -> Unit
            MovieCommentContract.MovieCommentState.Loading -> multiStateView.viewState = MultiStateView.ViewState.LOADING
            MovieCommentContract.MovieCommentState.Empty -> multiStateView.viewState = MultiStateView.ViewState.EMPTY
            is MovieCommentContract.MovieCommentState.Error -> multiStateView.viewState = MultiStateView.ViewState.ERROR
            is MovieCommentContract.MovieCommentState.Success -> {
                commentAdapter?.submitList(state.comments)
                rvComments.smoothScrollToPosition(0)
                multiStateView.viewState = MultiStateView.ViewState.CONTENT
            }
        }
    }

    override fun renderEffect(effect: MovieCommentContract.Effect) {
        when(effect) {
            is MovieCommentContract.Effect.ShowSnackBar -> showSnackbar(effect.message.asString(requireContext()), STATUS_ERROR)
        }
    }
}