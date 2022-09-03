package com.arch3rtemp.android.moviesapp.data.global.source

import com.arch3rtemp.android.moviesapp.data.global.api.LoginService
import com.arch3rtemp.android.moviesapp.data.global.dto.LoginDto
import javax.inject.Inject

class LoginRemoteDataSourceImpl @Inject constructor(
    private val loginService: LoginService
) : LoginRemoteDataSource {

    override suspend fun loginUser(login: LoginDto) {
        return loginService.login(login.user, login.password)
    }
}