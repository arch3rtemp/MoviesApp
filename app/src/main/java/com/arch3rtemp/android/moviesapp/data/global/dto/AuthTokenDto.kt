package com.arch3rtemp.android.moviesapp.data.global.dto

import com.google.gson.annotations.SerializedName

data class AuthTokenDto(
    @SerializedName("access_token")
    val accessToken: String = "",
    @SerializedName("token_type")
    val tokenType: String = "",
    @SerializedName("expires_in")
    val expiresIn: Long = -1
)