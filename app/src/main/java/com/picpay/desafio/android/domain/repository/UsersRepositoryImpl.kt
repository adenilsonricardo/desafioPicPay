package com.picpay.desafio.android.domain.repository

import com.picpay.desafio.android.data.models.User
import com.picpay.desafio.android.datasource.UsersDataSource
import kotlinx.coroutines.flow.Flow

class UsersRepositoryImpl(private val dataSource: UsersDataSource) : UsersRepository {
    override suspend fun getUsers(): Flow<List<User>> {
        return dataSource.getUsers()
    }
}