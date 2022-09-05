package com.arch3rtemp.android.moviesapp.data.mapper

import com.arch3rtemp.android.moviesapp.data.local.entity.MovieEntity
import com.arch3rtemp.android.moviesapp.domain.model.Movie
import com.arch3rtemp.android.moviesapp.util.Mapper
import javax.inject.Inject

class MovieEntityDomainMapper @Inject constructor() : Mapper<MovieEntity, Movie> {
    override fun from(i: MovieEntity?): Movie {
        return Movie(
            id = i?.id ?: -1,
            title = i?.title.orEmpty(),
            posterUrl = i?.posterUrl.orEmpty(),
            year = i?.year.orEmpty(),
            duration = i?.duration.orEmpty(),
            rating = i?.rating.orEmpty(),
            cast = i?.cast.orEmpty()
        )
    }

    override fun to(o: Movie?): MovieEntity {
        return MovieEntity(
            id = o?.id ?: -1,
            title = o?.title.orEmpty(),
            posterUrl = o?.posterUrl.orEmpty(),
            year = o?.year.orEmpty(),
            duration = o?.duration.orEmpty(),
            rating = o?.rating.orEmpty(),
            cast = o?.cast.orEmpty()
        )
    }
}