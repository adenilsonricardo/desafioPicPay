package com.picpay.desafio.android.datasource

import com.picpay.desafio.android.data.api.PicPayService
import com.picpay.desafio.android.data.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UsersDataSourceImpl(private val service: PicPayService) : UsersDataSource {
    override suspend fun getUsers(): Flow<List<User>> {
        return flow {
            emit(service.getUsers())
        }
    }
}