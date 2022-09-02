package com.arch3rtemp.android.moviesapp.data.global.dto

import com.squareup.moshi.Json

data class MovieDto(
    val id: Long?,
    val title: String?,
    @Json(name = "poster_url")
    val posterUrl: String?,
    val year: String?,
    val rating: String?
)
