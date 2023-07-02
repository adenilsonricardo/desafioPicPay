package com.picpay.desafio.android.data.datasource

import com.picpay.desafio.android.data.api.PicPayService
import com.picpay.desafio.android.data.models.User

class UsersDataSourceImpl(private val service: PicPayService) : UsersDataSource {
    override suspend fun getUsers(): List<User> {
        return service.getUsers()
    }
}