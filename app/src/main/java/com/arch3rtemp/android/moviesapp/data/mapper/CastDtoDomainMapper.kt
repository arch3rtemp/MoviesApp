package com.arch3rtemp.android.moviesapp.data.mapper

import com.arch3rtemp.android.moviesapp.data.global.dto.CastDto
import com.arch3rtemp.android.moviesapp.domain.model.Cast
import com.arch3rtemp.android.moviesapp.util.Mapper
import javax.inject.Inject

class CastDtoDomainMapper @Inject constructor() : Mapper<CastDto, Cast> {
    override fun from(i: CastDto?): Cast {
        return Cast(
            cast = i?.cast.orEmpty()
        )
    }

    override fun to(o: Cast?): CastDto {
        return CastDto(
            cast = o?.cast
        )
    }
}