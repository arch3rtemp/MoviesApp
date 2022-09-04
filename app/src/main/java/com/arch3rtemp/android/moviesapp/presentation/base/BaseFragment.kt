package com.arch3rtemp.android.moviesapp.presentation.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.arch3rtemp.android.moviesapp.R
import com.arch3rtemp.android.moviesapp.util.UiEffect
import com.arch3rtemp.android.moviesapp.util.UiEvent
import com.arch3rtemp.android.moviesapp.util.UiState
import kotlinx.coroutines.launch

abstract class BaseFragment<Event : UiEvent, State : UiState, Effect : UiEffect, VB : ViewBinding, VM : BaseViewModel<Event, State, Effect>> :
    Fragment() {

    private var _binding: VB? = null
    abstract val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> VB

    protected abstract val viewModel: VM

    protected val binding: VB
        get() = requireNotNull(_binding)

    private var progressDialog: Dialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = bindLayout.invoke(inflater, container, false)
        return requireNotNull(_binding).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    renderState(it)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.effect.collect {
                    renderEffect(it)
                }
            }
        }

        prepareView(savedInstanceState)
    }

    fun showProgressDialog(message: String) {
        progressDialog = Dialog(requireContext()).apply {
            setContentView(R.layout.dialog_progress)
            findViewById<TextView>(R.id.tv_progress_text).text = message
            setCancelable(false)
            setCanceledOnTouchOutside(false)
            show()
        }
    }

    fun hideProgressDialog() {
        progressDialog?.hide()
    }

    fun dismissProgressDialog() {
        progressDialog?.dismiss()
    }

    abstract fun prepareView(savedInstanceState: Bundle?)

    abstract fun renderState(state: State)

    abstract fun renderEffect(effect: Effect)

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}