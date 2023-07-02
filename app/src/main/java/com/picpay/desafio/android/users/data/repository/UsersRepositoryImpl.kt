package com.picpay.desafio.android.users.data.repository

import com.picpay.desafio.android.users.data.datasource.UsersDataSource
import com.picpay.desafio.android.users.data.mapper.UsersListMapper
import com.picpay.desafio.android.users.domain.models.UsersDomain
import com.picpay.desafio.android.users.domain.repository.UsersRepository

class UsersRepositoryImpl(
    private val mapper: UsersListMapper,
    private val dataSource: UsersDataSource
) : UsersRepository {
    override suspend fun getUsers(): List<UsersDomain> {
        return dataSource.getUsers().map { mapper.map(it) }
    }
}