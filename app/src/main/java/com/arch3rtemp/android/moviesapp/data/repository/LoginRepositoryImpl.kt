package com.arch3rtemp.android.moviesapp.data.repository

import com.arch3rtemp.android.moviesapp.domain.model.Login
import com.arch3rtemp.android.moviesapp.domain.repository.LoginRepository
import com.arch3rtemp.android.moviesapp.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(

) : LoginRepository {
    override fun loginUser(user: Login): Flow<Resource<Unit>> {
        TODO("Not yet implemented")
    }
}