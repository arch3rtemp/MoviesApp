package com.arch3rtemp.android.moviesapp.data.repository

import com.arch3rtemp.android.moviesapp.data.global.dto.LoginDto
import com.arch3rtemp.android.moviesapp.data.global.source.LoginRemoteDataSource
import com.arch3rtemp.android.moviesapp.domain.model.Login
import com.arch3rtemp.android.moviesapp.domain.repository.LoginRepository
import com.arch3rtemp.android.moviesapp.util.Mapper
import com.arch3rtemp.android.moviesapp.util.Resource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginRemoteDataSource: LoginRemoteDataSource,
    private val loginDtoDomainMapper: Mapper<LoginDto, Login>
) : LoginRepository {

    override fun loginUser(user: Login) = flow {
        emit(Resource.Loading)
        try {
            emit(Resource.Success(loginRemoteDataSource.loginUser(loginDtoDomainMapper.to(user))))
        } catch (exception: Exception) {
            emit(Resource.Error(exception))
        }
    }
}