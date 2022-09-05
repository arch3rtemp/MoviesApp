package com.arch3rtemp.android.moviesapp.domain.usecase.login

import com.arch3rtemp.android.moviesapp.domain.model.LoginRequest
import com.arch3rtemp.android.moviesapp.domain.repository.LoginRepository
import com.arch3rtemp.android.moviesapp.util.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class LoginUseCase(
    private val loginRepository: LoginRepository,
    private val dispatcher: CoroutineDispatcher
) {
    operator fun invoke(loginRequest: LoginRequest): Flow<Resource<Unit>> {
        return loginRepository.loginUser(loginRequest).flowOn(dispatcher)
    }
}