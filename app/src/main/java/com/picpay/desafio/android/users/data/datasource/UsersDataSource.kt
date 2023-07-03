package com.picpay.desafio.android.users.data.datasource

import com.picpay.desafio.android.users.data.models.User

interface UsersDataSource {
    suspend fun getUsers(): List<User>
}