package com.arch3rtemp.android.moviesapp.domain.usecase.login

import com.arch3rtemp.android.moviesapp.domain.repository.LoginRepository
import com.arch3rtemp.android.moviesapp.util.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class CheckTokenUseCase(
    private val loginRepository: LoginRepository,
    private val dispatcher: CoroutineDispatcher
) {
    operator fun invoke(): Flow<Resource<Boolean>> {
        return loginRepository.hasToken().flowOn(dispatcher)
    }
}