package com.arch3rtemp.android.moviesapp.domain.model

data class AuthToken(
    val accessToken: String = "",
    val tokenType: String = "",
    val expiresIn: Long = -1
)
