package com.picpay.desafio.android.users.domain.repository

import com.picpay.desafio.android.users.domain.models.UsersDomain

interface UsersRepository {
    suspend fun getUsers(): List<UsersDomain>
}