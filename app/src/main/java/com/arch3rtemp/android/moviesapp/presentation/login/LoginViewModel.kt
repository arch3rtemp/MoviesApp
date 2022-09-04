package com.arch3rtemp.android.moviesapp.presentation.login

import android.text.TextUtils
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arch3rtemp.android.moviesapp.R
import com.arch3rtemp.android.moviesapp.domain.model.Login
import com.arch3rtemp.android.moviesapp.domain.usecase.login.LoginUseCase
import com.arch3rtemp.android.moviesapp.presentation.base.BaseViewModel
import com.arch3rtemp.android.moviesapp.util.Constants.STATUS_ERROR
import com.arch3rtemp.android.moviesapp.util.Resource
import com.arch3rtemp.android.moviesapp.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) :
    BaseViewModel<LoginContract.Event, LoginContract.State, LoginContract.Effect>() {

    override fun createInitialState(): LoginContract.State {
        return LoginContract.State(loginState = LoginContract.LoginState.Idle)
    }

    override fun handleEvent(event: LoginContract.Event) {
        when(event) {
            is LoginContract.Event.OnLogin -> { loginUser(event.login) }
        }
    }

    private fun setStateError(message: UiText) {
        setState { copy(loginState = LoginContract.LoginState.Error) }
        setEffect { LoginContract.Effect.ShowSnackbar(message, STATUS_ERROR) }
    }

    private fun loginUser(login: Login) {
        viewModelScope.launch {
            if (validateLoginDetails(login)) {
                loginUseCase(login)
                    .collectLatest {
                        when(it) {
                            Resource.Empty -> Unit
                            Resource.Loading -> setState { copy(loginState = LoginContract.LoginState.Loading) }
                            is Resource.Error -> setStateError(it.message)
                            is Resource.Success -> {
                                setState { copy(loginState = LoginContract.LoginState.Idle) }
                                setState { copy(loginState = LoginContract.LoginState.Idle) }
                                setEffect { LoginContract.Effect.Finish }
                            }
                        }
                    }
            }
        }
    }

    private fun validateLoginDetails(login: Login): Boolean {
        login.apply {
            return when {
                TextUtils.isEmpty(username.trim { it <= ' ' }) -> {
                    setStateError(UiText.StringResource(R.string.error_email))
                    false
                }
                TextUtils.isEmpty(password.trim { it <= ' ' }) -> {
                    setStateError(UiText.StringResource(R.string.error_password))
                    false
                }
                else -> {
                    true
                }
            }
        }
    }
}