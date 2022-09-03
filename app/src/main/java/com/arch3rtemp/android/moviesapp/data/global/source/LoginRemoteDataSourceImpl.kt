package com.arch3rtemp.android.moviesapp.data.global.source

import com.arch3rtemp.android.moviesapp.data.global.api.LoginService
import com.arch3rtemp.android.moviesapp.data.global.dto.LoginDto
import com.arch3rtemp.android.moviesapp.data.global.dto.AuthTokenDto
import retrofit2.Response
import javax.inject.Inject

class LoginRemoteDataSourceImpl @Inject constructor(
    private val loginService: LoginService
) : LoginRemoteDataSource {

    override suspend fun loginUser(login: LoginDto): Response<AuthTokenDto> {
        return loginService.login(login)
    }
}