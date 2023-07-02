package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.datasource.UsersDataSource
import com.picpay.desafio.android.data.models.User
import com.picpay.desafio.android.domain.repository.UsersRepository

class UsersRepositoryImpl(
    private val dataSource: UsersDataSource,
) : UsersRepository {
    override suspend fun getUsers(): List<User> {
        return dataSource.getUsers()
    }
}