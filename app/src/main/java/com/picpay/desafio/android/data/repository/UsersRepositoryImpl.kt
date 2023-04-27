package com.picpay.desafio.android.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.picpay.desafio.android.data.datasource.UsersDataSource
import com.picpay.desafio.android.data.models.User
import com.picpay.desafio.android.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow

class UsersRepositoryImpl(
    private val dataSource: UsersDataSource,
) : UsersRepository {
    @RequiresApi(Build.VERSION_CODES.N)
    override suspend fun getUsers(): Flow<List<User>> {
        return dataSource.getUsers()
    }
}