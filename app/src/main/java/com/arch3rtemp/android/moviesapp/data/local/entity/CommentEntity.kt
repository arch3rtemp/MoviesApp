package com.arch3rtemp.android.moviesapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comment_table")
data class CommentEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    @ColumnInfo(name = "movie_id")
    val movieId: String,
    val message: String,
    @ColumnInfo(name = "created_at")
    val createdAt: String
)
