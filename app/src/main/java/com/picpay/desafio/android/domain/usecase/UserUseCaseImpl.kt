package com.picpay.desafio.android.domain.usecase

import com.picpay.desafio.android.data.models.User
import com.picpay.desafio.android.domain.repository.UsersRepository
import com.picpay.desafio.android.presentation.usecase.UserUseCase
import kotlinx.coroutines.flow.Flow

class UserUseCaseImpl(
    private val repository: UsersRepository
) : UserUseCase {
    override suspend operator fun invoke(): Flow<List<User>> {
        return repository.getUsers()
    }
}