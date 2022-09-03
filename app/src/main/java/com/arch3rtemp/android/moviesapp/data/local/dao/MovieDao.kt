package com.arch3rtemp.android.moviesapp.data.local.dao

import androidx.room.*
import com.arch3rtemp.android.moviesapp.data.local.entity.CastEntity
import com.arch3rtemp.android.moviesapp.data.local.entity.CommentEntity
import com.arch3rtemp.android.moviesapp.data.local.entity.MovieEntity

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Update(entity = MovieEntity::class)
    suspend fun insertCast(movie: CastEntity)

    @Query("SELECT * FROM movie_table")
    fun selectMovies(): List<MovieEntity>

    @Query("SELECT * FROM movie_table WHERE id == :id")
    fun selectMovie(id: Long): MovieEntity

    @Query("DELETE FROM movie_table")
    suspend fun deleteMovies()

}