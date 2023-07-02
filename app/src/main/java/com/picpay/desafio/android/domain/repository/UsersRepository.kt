package com.picpay.desafio.android.domain.repository

import com.picpay.desafio.android.data.models.User

interface UsersRepository {
    suspend fun getUsers(): List<User>
}