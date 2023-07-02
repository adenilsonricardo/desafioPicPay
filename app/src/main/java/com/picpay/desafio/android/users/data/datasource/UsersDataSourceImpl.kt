package com.picpay.desafio.android.users.data.datasource

import com.picpay.desafio.android.users.data.api.PicPayService
import com.picpay.desafio.android.users.data.models.User

class UsersDataSourceImpl(private val service: PicPayService) : UsersDataSource {
    override suspend fun getUsers(): List<User> {
        return service.getUsers()
    }
}