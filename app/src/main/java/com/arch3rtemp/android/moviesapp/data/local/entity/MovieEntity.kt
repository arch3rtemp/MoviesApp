package com.arch3rtemp.android.moviesapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_table")
data class MovieEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val title: String,
    @ColumnInfo(name = "poster_url")
    val posterUrl: String,
    val year: String,
    val duration: String,
    val rating: String,
    val cast: List<String>
)