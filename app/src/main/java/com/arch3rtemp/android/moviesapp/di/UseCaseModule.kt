package com.arch3rtemp.android.moviesapp.di

import com.arch3rtemp.android.moviesapp.domain.repository.LoginRepository
import com.arch3rtemp.android.moviesapp.domain.repository.MovieRepository
import com.arch3rtemp.android.moviesapp.domain.usecase.login.CheckTokenUseCase
import com.arch3rtemp.android.moviesapp.domain.usecase.login.LoginUseCase
import com.arch3rtemp.android.moviesapp.domain.usecase.movie.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideLoginUseCase(loginRepository: LoginRepository): LoginUseCase {
        return LoginUseCase(loginRepository, Dispatchers.IO)
    }

    @Provides
    @ViewModelScoped
    fun provideCheckTokenUseCase(loginRepository: LoginRepository): CheckTokenUseCase {
        return CheckTokenUseCase(loginRepository, Dispatchers.IO)
    }

    @Provides
    @ViewModelScoped
    fun provideCacheCastUseCase(movieRepository: MovieRepository): CacheCastUseCase {
        return CacheCastUseCase(movieRepository, Dispatchers.IO)
    }

    @Provides
    @ViewModelScoped
    fun provideCacheCommentsUseCase(movieRepository: MovieRepository): CacheCommentsUseCase {
        return CacheCommentsUseCase(movieRepository, Dispatchers.IO)
    }

    @Provides
    @ViewModelScoped
    fun provideCacheMoviesUseCase(movieRepository: MovieRepository): CacheMoviesUseCase {
        return CacheMoviesUseCase(movieRepository, Dispatchers.IO)
    }

    @Provides
    @ViewModelScoped
    fun provideLoadCommentsUseCase(movieRepository: MovieRepository): LoadCommentsUseCase {
        return LoadCommentsUseCase(movieRepository, Dispatchers.IO)
    }

    @Provides
    @ViewModelScoped
    fun provideLoadMoviesUseCase(movieRepository: MovieRepository): LoadMoviesUseCase {
        return LoadMoviesUseCase(movieRepository, Dispatchers.IO)
    }

    @Provides
    @ViewModelScoped
    fun provideLoadMovieUseCase(movieRepository: MovieRepository): LoadMovieUseCase {
        return LoadMovieUseCase(movieRepository, Dispatchers.IO)
    }

    @Provides
    @ViewModelScoped
    fun providePostCommentUseCase(movieRepository: MovieRepository): PostCommentUseCase {
        return PostCommentUseCase(movieRepository, Dispatchers.IO)
    }
}