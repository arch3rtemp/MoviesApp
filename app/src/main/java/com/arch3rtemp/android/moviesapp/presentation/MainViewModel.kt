package com.arch3rtemp.android.moviesapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arch3rtemp.android.moviesapp.domain.usecase.login.CheckTokenUseCase
import com.arch3rtemp.android.moviesapp.util.Resource
import com.arch3rtemp.android.moviesapp.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val checkTokenUseCase: CheckTokenUseCase
) : ViewModel() {

    private val _hasToken = MutableLiveData<Boolean>()
    val hasToken: LiveData<Boolean> get() = _hasToken

    private val _errorChannel = Channel<UiText>()
    val errorChannel = _errorChannel.receiveAsFlow()

    init {
        checkToken()
    }

    private fun checkToken() {
        viewModelScope.launch {
            checkTokenUseCase()
                .collectLatest {
                    when (it) {
                        Resource.Loading -> Unit
                        Resource.Empty -> Unit
                        is Resource.Error -> _errorChannel.trySend(it.message)
                        is Resource.Success -> _hasToken.postValue(it.data!!)
                    }
                }
        }
    }
}