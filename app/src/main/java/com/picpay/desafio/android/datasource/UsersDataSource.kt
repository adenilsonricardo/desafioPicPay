package com.picpay.desafio.android.datasource

import com.picpay.desafio.android.data.models.User
import kotlinx.coroutines.flow.Flow

interface UsersDataSource {
    suspend fun getUsers(): Flow<List<User>>
}