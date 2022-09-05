package com.arch3rtemp.android.moviesapp.data.mapper

import com.arch3rtemp.android.moviesapp.data.global.dto.LoginRequestDto
import com.arch3rtemp.android.moviesapp.domain.model.LoginRequest
import com.arch3rtemp.android.moviesapp.util.Mapper
import javax.inject.Inject

class LoginDtoDomainMapper @Inject constructor() : Mapper<LoginRequestDto, LoginRequest> {
    override fun from(i: LoginRequestDto?): LoginRequest {
        return LoginRequest(
            username = i?.username.orEmpty(),
            password = i?.password.orEmpty()
        )
    }

    override fun to(o: LoginRequest?): LoginRequestDto {
        return LoginRequestDto(
            username = o?.username.orEmpty(),
            password = o?.password.orEmpty()
        )
    }
}