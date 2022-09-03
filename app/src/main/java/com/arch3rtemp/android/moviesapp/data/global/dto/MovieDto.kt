package com.arch3rtemp.android.moviesapp.data.global.dto

import com.google.gson.annotations.SerializedName

data class MovieDto(
    val id: Long?,
    val title: String?,
    @SerializedName("poster_url")
    val posterUrl: String?,
    val year: String?,
    val rating: String?
)
