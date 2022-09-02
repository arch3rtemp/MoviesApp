package com.arch3rtemp.android.moviesapp.data.global.dto

import com.squareup.moshi.Json

data class CommentDto(
    val id: String?,
    @Json(name = "movie_id")
    val movieId: String?,
    val message: String?,
    @Json(name = "created_at")
    val createdAt: String?
)
