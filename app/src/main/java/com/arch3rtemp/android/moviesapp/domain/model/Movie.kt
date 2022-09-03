package com.arch3rtemp.android.moviesapp.domain.model

data class Movie(
    val id: Long = -1L,
    val title: String = "",
    val posterUrl: String = "",
    val year: String = "",
    val rating: String = "",
    val cast: List<String> = listOf()
)