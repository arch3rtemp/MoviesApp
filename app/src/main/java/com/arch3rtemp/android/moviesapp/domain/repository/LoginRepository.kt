package com.arch3rtemp.android.moviesapp.domain.repository

import com.arch3rtemp.android.moviesapp.domain.model.LoginRequest
import com.arch3rtemp.android.moviesapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    fun loginUser(user: LoginRequest): Flow<Resource<Unit>>
    fun hasToken(): Flow<Resource<Boolean>>
    fun logOutUser(): Flow<Resource<Unit>>
}