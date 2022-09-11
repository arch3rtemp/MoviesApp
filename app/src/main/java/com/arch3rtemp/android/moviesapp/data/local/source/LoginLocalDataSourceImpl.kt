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
        var accessToken = ""
        var tokenType = ""
        var expiresIn = -1L

        if (sharedPreferences.contains(ACCESS_TOKEN_KEY)) {
            accessToken = sharedPreferences.getString("accessToken", "").orEmpty()
        }
        if (sharedPreferences.contains(TOKEN_TYPE_KEY)) {
            tokenType = sharedPreferences.getString("tokenType", "").orEmpty()
        }
        if (sharedPreferences.contains(EXPIRES_IN_KEY)) {
            expiresIn = sharedPreferences.getLong("expiresIn", -1L)
        }
        return AuthToken(
            accessToken = accessToken,
            tokenType = tokenType,
            expiresIn = expiresIn
        )
    }

    override suspend fun deleteToken() {
        sharedPreferences.edit().clear().apply()
    }

    companion object {
        const val ACCESS_TOKEN_KEY = "accessToken"
        const val TOKEN_TYPE_KEY = "tokenType"
        const val EXPIRES_IN_KEY = "expiresIn"
    }
}