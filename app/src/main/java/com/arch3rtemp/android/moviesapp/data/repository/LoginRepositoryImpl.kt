package com.arch3rtemp.android.moviesapp.data.repository

import com.arch3rtemp.android.moviesapp.data.global.dto.AuthTokenDto
import com.arch3rtemp.android.moviesapp.data.global.dto.LoginRequestDto
import com.arch3rtemp.android.moviesapp.data.global.source.LoginRemoteDataSource
import com.arch3rtemp.android.moviesapp.data.local.source.LoginLocalDataSource
import com.arch3rtemp.android.moviesapp.domain.model.AuthToken
import com.arch3rtemp.android.moviesapp.domain.model.LoginRequest
import com.arch3rtemp.android.moviesapp.domain.repository.LoginRepository
import com.arch3rtemp.android.moviesapp.util.Mapper
import com.arch3rtemp.android.moviesapp.util.Resource
import com.arch3rtemp.android.moviesapp.util.UiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginRemoteDataSource: LoginRemoteDataSource,
    private val loginRequestDtoDomainMapper: Mapper<LoginRequestDto, LoginRequest>,
    private val loginLocalDataSource: LoginLocalDataSource,
    private val authTokenDtoDomainMapper: Mapper<AuthTokenDto, AuthToken>
) : LoginRepository {

    override fun loginUser(user: LoginRequest) = flow {
        emit(Resource.Loading)
        try {
            val response = loginRemoteDataSource.loginUser(loginRequestDtoDomainMapper.to(user))
            if (response.isSuccessful) {
                val token = response.body()
                emit(Resource.Success(loginLocalDataSource.saveToken(authTokenDtoDomainMapper.from(token))))
            } else {
                emit(Resource.Error(UiText.DynamicString(response.message())))
            }
        } catch (exception: Exception) {
            emit(Resource.Error(UiText.DynamicString(exception.message.toString())))
        }
    }

    override fun hasToken() = flow {
        emit(Resource.Loading)
        try {
            val token = loginLocalDataSource.loadToken()
            if (token.accessToken.isNotBlank()) {
                emit(Resource.Success(true))
            } else {
                emit(Resource.Success(false))
            }
        } catch (exception: Exception) {
            emit(Resource.Error(UiText.DynamicString(exception.message.toString())))
        }
    }
}