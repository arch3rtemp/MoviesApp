package com.arch3rtemp.android.moviesapp.data.mapper

import com.arch3rtemp.android.moviesapp.data.local.entity.CastEntity
import com.arch3rtemp.android.moviesapp.domain.model.Cast
import com.arch3rtemp.android.moviesapp.util.Mapper
import javax.inject.Inject

class CastEntityDomainMapper @Inject constructor() : Mapper<CastEntity, Cast> {
    override fun from(i: CastEntity?): Cast {
        return Cast(
            id = i?.id ?: -1,
            cast = i?.cast.orEmpty()
        )
    }

    override fun to(o: Cast?): CastEntity {
        return CastEntity(
            id = o?.id ?: -1,
            cast = o?.cast.orEmpty()
        )
    }
}