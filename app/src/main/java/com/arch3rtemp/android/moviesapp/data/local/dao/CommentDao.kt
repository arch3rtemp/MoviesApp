package com.arch3rtemp.android.moviesapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arch3rtemp.android.moviesapp.data.local.entity.CommentEntity

@Dao
interface CommentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComments(comments: List<CommentEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComment(comment: CommentEntity)

    @Query("SELECT * FROM comment_table WHERE movie_id == :id")
    fun selectComments(id: String): List<CommentEntity>

    @Query("DELETE FROM comment_table")
    suspend fun deleteComments()

}