package com.arch3rtemp.android.moviesapp.domain.repository

import com.arch3rtemp.android.moviesapp.domain.model.Login
import com.arch3rtemp.android.moviesapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    fun loginUser(user: Login): Flow<Resource<Unit>>
}