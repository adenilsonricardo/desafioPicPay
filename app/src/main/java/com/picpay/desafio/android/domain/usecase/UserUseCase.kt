package com.picpay.desafio.android.domain.usecase

import com.picpay.desafio.android.data.models.User
import com.picpay.desafio.android.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow

class UserUseCase(
    private val repository: UsersRepository
) {
    suspend operator fun invoke(): Flow<List<User>> {
        return repository.getUsers()
    }
}