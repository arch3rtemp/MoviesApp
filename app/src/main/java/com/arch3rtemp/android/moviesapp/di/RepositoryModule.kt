package com.arch3rtemp.android.moviesapp.di

import com.arch3rtemp.android.moviesapp.data.repository.LoginRepositoryImpl
import com.arch3rtemp.android.moviesapp.data.repository.MovieRepositoryImpl
import com.arch3rtemp.android.moviesapp.domain.repository.LoginRepository
import com.arch3rtemp.android.moviesapp.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMovieRepository(movieRepository: MovieRepositoryImpl): MovieRepository

    @Binds
    @Singleton
    abstract fun bindLoginRepository(loginRepository: LoginRepositoryImpl): LoginRepository
}