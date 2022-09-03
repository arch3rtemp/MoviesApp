package com.arch3rtemp.android.moviesapp.data.global.api

import com.arch3rtemp.android.moviesapp.data.global.dto.LoginDto
import com.arch3rtemp.android.moviesapp.data.global.dto.AuthTokenDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginService {

    @POST("Login")
    @FormUrlEncoded
    suspend fun login(@Body loginDto: LoginDto): Response<AuthTokenDto>

}