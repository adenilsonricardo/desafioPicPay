package com.picpay.desafio.android.domain.repository

import com.picpay.desafio.android.data.models.User
import kotlinx.coroutines.flow.Flow

interface UsersRepository {
    suspend fun getUsers(): Flow<List<User>>
}