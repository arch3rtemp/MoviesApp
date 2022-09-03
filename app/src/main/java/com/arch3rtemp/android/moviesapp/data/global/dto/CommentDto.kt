package com.arch3rtemp.android.moviesapp.data.global.dto

import com.google.gson.annotations.SerializedName


data class CommentDto(
    val id: String?,
    @SerializedName("movie_id")
    val movieId: String?,
    val message: String?,
    @SerializedName("created_at")
    val createdAt: String?
)
