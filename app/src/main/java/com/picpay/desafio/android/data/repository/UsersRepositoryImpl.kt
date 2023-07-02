package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.datasource.UsersDataSource
import com.picpay.desafio.android.data.mapper.UsersListMapper
import com.picpay.desafio.android.domain.models.UsersDomain
import com.picpay.desafio.android.domain.repository.UsersRepository

class UsersRepositoryImpl(
    private val mapper: UsersListMapper,
    private val dataSource: UsersDataSource
) : UsersRepository {
    override suspend fun getUsers(): List<UsersDomain> {
        return dataSource.getUsers().map { mapper.map(it) }
    }
}