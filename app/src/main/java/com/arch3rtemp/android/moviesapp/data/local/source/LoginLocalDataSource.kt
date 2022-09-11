package com.arch3rtemp.android.moviesapp.data.local.source

import com.arch3rtemp.android.moviesapp.domain.model.AuthToken

interface LoginLocalDataSource {
    suspend fun saveToken(authToken: AuthToken)
    fun loadToken(): AuthToken
    suspend fun deleteToken()
}