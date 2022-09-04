package com.arch3rtemp.android.moviesapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CastEntity(
    val id: Long,
    val cast: List<String>
)