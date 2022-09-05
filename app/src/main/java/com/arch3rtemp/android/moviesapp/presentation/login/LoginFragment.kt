package com.arch3rtemp.android.moviesapp.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.arch3rtemp.android.moviesapp.R
import com.arch3rtemp.android.moviesapp.databinding.FragmentLoginBinding
import com.arch3rtemp.android.moviesapp.domain.model.LoginRequest
import com.arch3rtemp.android.moviesapp.presentation.base.BaseFragment
import com.arch3rtemp.android.moviesapp.util.showSnackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginContract.Event, LoginContract.State, LoginContract.Effect, FragmentLoginBinding, LoginViewModel>() {

    override val viewModel by viewModels<LoginViewModel>()

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding
        get() = FragmentLoginBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) {
        setListeners()
    }

    private fun setListeners() = with(binding) {
        btnSignIn.setOnClickListener {
            viewModel.setEvent(LoginContract.Event.OnLogin(getDataFromFields()))
        }
    }

    private fun getDataFromFields() = with(binding) {
        return@with LoginRequest(
            username = etUsername.text.toString(),
            password = etPassword.text.toString()
        )
    }

    override fun renderState(state: LoginContract.State) {
        when(state.loginState) {
            LoginContract.LoginState.Idle -> showLoginIdle()
            LoginContract.LoginState.Loading -> showLoginLoading()
            LoginContract.LoginState.Error -> showLoginError()
            LoginContract.LoginState.Success -> showLoginSuccess()
        }
    }

    override fun renderEffect(effect: LoginContract.Effect) {
        when(effect) {
            is LoginContract.Effect.ShowSnackbar -> showSnackbar(effect.message.asString(requireContext()), effect.status)
            LoginContract.Effect.Finish -> findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToListFragment())
        }
    }

    private fun showLoginIdle() {
        dismissProgressDialog()
    }

    private fun showLoginLoading() {
        showProgressDialog(resources.getString(R.string.please_wait))
    }

    private fun showLoginSuccess() {
        dismissProgressDialog()
    }

    private fun showLoginError() {
        dismissProgressDialog()
    }
}