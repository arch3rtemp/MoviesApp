package com.arch3rtemp.android.moviesapp.data.global.source

import com.arch3rtemp.android.moviesapp.data.global.dto.LoginDto


interface LoginRemoteDataSource {
    suspend fun loginUser(login: LoginDto)
}