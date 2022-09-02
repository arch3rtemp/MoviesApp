package com.arch3rtemp.android.moviesapp.data.global.dto

import com.squareup.moshi.Json

data class LoginResponse(
    @Json(name = "access_token")
    val accessToken: String = "",
    @Json(name = "token_type")
    val tokenType: String = "",
    @Json(name = "expires_in")
    val expiresIn: Int = -1
)