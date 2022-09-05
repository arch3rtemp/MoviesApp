package com.arch3rtemp.android.moviesapp.di

import com.arch3rtemp.android.moviesapp.data.global.source.*
import com.arch3rtemp.android.moviesapp.data.local.source.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SourceModule {

    @Binds
    @Singleton
    abstract fun bindMovieRemoteDataSource(movieRemoteDataSource: MovieRemoteDataSourceImpl): MovieRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindMovieLocalDataSource(movieLocalDataSource: MovieLocalDataSourceImpl): MovieLocalDataSource

    @Binds
    @Singleton
    abstract fun bindLoginRemoteDataSource(loginRemoteDataSource: LoginRemoteDataSourceImpl): LoginRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindLoginLocalDataSource(loginLocalDataSource: LoginLocalDataSourceImpl): LoginLocalDataSource

    @Binds
    @Singleton
    abstract fun bindCommentRemoteDataSource(commentRemoteDataSource: CommentRemoteDataSourceImpl): CommentRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindCommentLocalDataSource(commentLocalDataSource: CommentLocalDataSourceImpl): CommentLocalDataSource
}