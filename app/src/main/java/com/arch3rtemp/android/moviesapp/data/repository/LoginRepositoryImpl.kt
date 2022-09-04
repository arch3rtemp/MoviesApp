package com.arch3rtemp.android.moviesapp.data.repository

import android.util.Log
import com.arch3rtemp.android.moviesapp.data.global.dto.LoginDto
import com.arch3rtemp.android.moviesapp.data.global.dto.AuthTokenDto
import com.arch3rtemp.android.moviesapp.data.global.source.LoginRemoteDataSource
import com.arch3rtemp.android.moviesapp.data.local.source.LoginLocalDataSource
import com.arch3rtemp.android.moviesapp.domain.model.Login
import com.arch3rtemp.android.moviesapp.domain.model.AuthToken
import com.arch3rtemp.android.moviesapp.domain.repository.LoginRepository
import com.arch3rtemp.android.moviesapp.util.Mapper
import com.arch3rtemp.android.moviesapp.util.Resource
import com.arch3rtemp.android.moviesapp.util.UiText
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginRemoteDataSource: LoginRemoteDataSource,
    private val loginDtoDomainMapper: Mapper<LoginDto, Login>,
    private val loginLocalDataSource: LoginLocalDataSource,
    private val authTokenDtoDomainMapper: Mapper<AuthTokenDto, AuthToken>
) : LoginRepository {

    override fun loginUser(user: Login) = flow {
        emit(Resource.Loading)
        try {
            val response = loginRemoteDataSource.loginUser(loginDtoDomainMapper.to(user))
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
}