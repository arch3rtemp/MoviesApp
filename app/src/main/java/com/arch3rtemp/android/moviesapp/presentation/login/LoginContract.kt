package com.arch3rtemp.android.moviesapp.presentation.login

import com.arch3rtemp.android.moviesapp.domain.model.LoginRequest
import com.arch3rtemp.android.moviesapp.util.UiEffect
import com.arch3rtemp.android.moviesapp.util.UiEvent
import com.arch3rtemp.android.moviesapp.util.UiState
import com.arch3rtemp.android.moviesapp.util.UiText

class LoginContract {
    sealed class LoginState {
        object Idle : LoginState()
        object Loading : LoginState()
        object Success : LoginState()
        object Error : LoginState()
    }

    sealed class Event : UiEvent {
        data class OnLogin(val loginRequest: LoginRequest) : Event()
    }

    sealed class Effect : UiEffect {
        data class ShowSnackbar(val message: UiText, val status: String) : Effect()
        object Finish : Effect()
    }

    data class State(
        val loginState: LoginState,
    ) : UiState
}