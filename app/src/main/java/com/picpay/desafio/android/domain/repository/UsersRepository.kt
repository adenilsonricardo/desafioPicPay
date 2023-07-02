package com.picpay.desafio.android.domain.repository

import com.picpay.desafio.android.domain.models.UsersDomain

interface UsersRepository {
    suspend fun getUsers(): List<UsersDomain>
}