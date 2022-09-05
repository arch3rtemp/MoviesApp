package com.arch3rtemp.android.moviesapp.di

import com.arch3rtemp.android.moviesapp.data.global.dto.*
import com.arch3rtemp.android.moviesapp.data.local.entity.CastEntity
import com.arch3rtemp.android.moviesapp.data.local.entity.CommentEntity
import com.arch3rtemp.android.moviesapp.data.local.entity.MovieEntity
import com.arch3rtemp.android.moviesapp.data.mapper.*
import com.arch3rtemp.android.moviesapp.domain.model.*
import com.arch3rtemp.android.moviesapp.util.Mapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

    @Singleton
    @Binds
    abstract fun bindsCastDtoDomainModel(mapper: CastDtoDomainMapper): Mapper<CastDto, Cast>

    @Singleton
    @Binds
    abstract fun bindsCastEntityDomainModel(mapper: CastEntityDomainMapper): Mapper<CastEntity, Cast>

    @Singleton
    @Binds
    abstract fun bindsCommentDtoDomainModel(mapper: CommentDtoDomainMapper): Mapper<CommentDto, Comment>

    @Singleton
    @Binds
    abstract fun bindsCommentEntityDomainModel(mapper: CommentEntityDomainMapper): Mapper<CommentEntity, Comment>

    @Singleton
    @Binds
    abstract fun bindsLoginDtoDomainModel(mapper: LoginDtoDomainMapper): Mapper<LoginRequestDto, LoginRequest>

    @Singleton
    @Binds
    abstract fun bindsMovieDtoDomainModel(mapper: MovieDtoDomainMapper): Mapper<MovieDto, Movie>

    @Singleton
    @Binds
    abstract fun bindsMovieEntityDomainModel(mapper: MovieEntityDomainMapper): Mapper<MovieEntity, Movie>

    @Singleton
    @Binds
    abstract fun bindsTokenDtoDomainMapper(mapper: AuthTokenDtoDomainMapper): Mapper<AuthTokenDto, AuthToken>
}