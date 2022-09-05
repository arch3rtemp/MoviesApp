package com.arch3rtemp.android.moviesapp.data.mapper

import com.arch3rtemp.android.moviesapp.data.global.dto.MovieDto
import com.arch3rtemp.android.moviesapp.domain.model.Movie
import com.arch3rtemp.android.moviesapp.util.Mapper
import javax.inject.Inject

class MovieDtoDomainMapper @Inject constructor() : Mapper<MovieDto, Movie> {

    override fun from(i: MovieDto?): Movie {
        return Movie(
            id = i?.id ?: -1,
            title = i?.title.orEmpty(),
            posterUrl = i?.posterUrl.orEmpty(),
            year = i?.year.orEmpty(),
            duration = i?.duration.orEmpty(),
            rating = i?.rating.orEmpty()
        )
    }

    override fun to(o: Movie?): MovieDto {
        return MovieDto(
            id = o?.id ?: -1,
            title = o?.title.orEmpty(),
            posterUrl = o?.posterUrl.orEmpty(),
            year = o?.year.orEmpty(),
            duration = o?.duration.orEmpty(),
            rating = o?.rating.orEmpty()
        )
    }
}