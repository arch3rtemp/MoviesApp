package com.arch3rtemp.android.moviesapp.data.global.api

import com.arch3rtemp.android.moviesapp.data.global.dto.LoginRequestDto
import com.arch3rtemp.android.moviesapp.data.global.dto.AuthTokenDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("Login")
    suspend fun login(@Body loginRequestDto: LoginRequestDto): Response<AuthTokenDto>

}