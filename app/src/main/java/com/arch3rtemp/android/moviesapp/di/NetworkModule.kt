package com.arch3rtemp.android.moviesapp.di

import com.arch3rtemp.android.moviesapp.data.global.api.MovieService
import com.arch3rtemp.android.moviesapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitInterface(): Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    @Provides
    @Singleton
    fun provideMovieApi(retrofit: Retrofit): MovieService =
        retrofit.create(MovieService::class.java)
}