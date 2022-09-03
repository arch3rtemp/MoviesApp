package com.arch3rtemp.android.moviesapp.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.arch3rtemp.android.moviesapp.data.local.converter.AllTypeConverters
import com.arch3rtemp.android.moviesapp.data.local.dao.MovieDao
import com.arch3rtemp.android.moviesapp.data.local.entity.CommentEntity
import com.arch3rtemp.android.moviesapp.data.local.entity.MovieEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Database(entities = [MovieEntity::class, CommentEntity::class], version = 1, exportSchema = false)
    @TypeConverters(AllTypeConverters::class)
    abstract class AppDatabase : RoomDatabase() {
        abstract fun movieDao(): MovieDao
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = Room
        .databaseBuilder(appContext, AppDatabase::class.java,"db")
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideMovieDao(database: AppDatabase): MovieDao = database.movieDao()

}