package com.arch3rtemp.android.moviesapp.data.global.source

import com.arch3rtemp.android.moviesapp.data.global.dto.LoginRequestDto
import com.arch3rtemp.android.moviesapp.data.global.dto.AuthTokenDto
import retrofit2.Response


interface LoginRemoteDataSource {
    suspend fun loginUser(login: LoginRequestDto): Response<AuthTokenDto>
}