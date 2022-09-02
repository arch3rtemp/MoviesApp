package com.arch3rtemp.android.moviesapp.domain.model

data class Movie(
    private val id: Long = -1L,
    private val title: String = "",
    private val posterUrl: String = "",
    private val year: String = "",
    private val rating: String = ""
)