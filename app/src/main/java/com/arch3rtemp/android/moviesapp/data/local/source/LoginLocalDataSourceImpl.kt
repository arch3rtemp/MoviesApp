package com.arch3rtemp.android.moviesapp.data.local.source

import android.content.SharedPreferences
import com.arch3rtemp.android.moviesapp.domain.model.AuthToken
import javax.inject.Inject

class LoginLocalDataSourceImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : LoginLocalDataSource {

    override suspend fun saveToken(authToken: AuthToken) {
        sharedPreferences.edit()
            .putString("accessToken", authToken.accessToken)
            .putString("tokenType", authToken.tokenType)
            .putLong("expiresIn", authToken.expiresIn)
            .apply()
    }

    override fun loadToken(): AuthToken {
        return AuthToken(
            accessToken = sharedPreferences.getString("accessToken", "").orEmpty(),
            tokenType = sharedPreferences.getString("tokenType", "").orEmpty(),
            expiresIn = sharedPreferences.getLong("expiresIn", -1)
        )
    }
}