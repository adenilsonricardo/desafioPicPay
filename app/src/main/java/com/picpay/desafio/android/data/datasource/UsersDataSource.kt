package com.picpay.desafio.android.data.datasource

import com.picpay.desafio.android.data.models.User

interface UsersDataSource {
    suspend fun getUsers(): List<User>
}