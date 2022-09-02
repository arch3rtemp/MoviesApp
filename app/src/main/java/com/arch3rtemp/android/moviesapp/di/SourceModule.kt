package com.arch3rtemp.android.moviesapp.di

import com.arch3rtemp.android.moviesapp.data.global.source.LoginRemoteDataSource
import com.arch3rtemp.android.moviesapp.data.global.source.LoginRemoteDataSourceImpl
import com.arch3rtemp.android.moviesapp.data.global.source.MovieRemoteDataSource
import com.arch3rtemp.android.moviesapp.data.global.source.MovieRemoteDataSourceImpl
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
    abstract fun bindLoginRemoteDataSource(loginRemoteDataSource: LoginRemoteDataSourceImpl): LoginRemoteDataSource
}