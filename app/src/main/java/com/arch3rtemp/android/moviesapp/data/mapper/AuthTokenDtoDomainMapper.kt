package com.arch3rtemp.android.moviesapp.data.mapper

import com.arch3rtemp.android.moviesapp.data.global.dto.AuthTokenDto
import com.arch3rtemp.android.moviesapp.domain.model.AuthToken
import com.arch3rtemp.android.moviesapp.util.Mapper
import javax.inject.Inject

class AuthTokenDtoDomainMapper @Inject constructor() : Mapper<AuthTokenDto, AuthToken> {
    override fun from(i: AuthTokenDto?): AuthToken {
        return AuthToken(
            accessToken = i?.accessToken.orEmpty(),
            tokenType = i?.tokenType.orEmpty(),
            expiresIn = i?.expiresIn ?: -1L
        )
    }

    override fun to(o: AuthToken?): AuthTokenDto {
        return AuthTokenDto(
            accessToken = o?.accessToken.orEmpty(),
            tokenType = o?.tokenType.orEmpty(),
            expiresIn = o?.expiresIn ?: -1L
        )
    }
}