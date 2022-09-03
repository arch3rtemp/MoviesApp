package com.arch3rtemp.android.moviesapp.data.mapper

import com.arch3rtemp.android.moviesapp.data.global.dto.LoginDto
import com.arch3rtemp.android.moviesapp.domain.model.Login
import com.arch3rtemp.android.moviesapp.util.Mapper
import javax.inject.Inject

class LoginDtoDomainMapper @Inject constructor() : Mapper<LoginDto, Login> {
    override fun from(i: LoginDto?): Login {
        return Login(
            username = i?.username.orEmpty(),
            password = i?.password.orEmpty()
        )
    }

    override fun to(o: Login?): LoginDto {
        return LoginDto(
            username = o?.username.orEmpty(),
            password = o?.password.orEmpty()
        )
    }
}