package com.picpay.desafio.android.users.domain.usecase

import com.picpay.desafio.android.users.domain.models.UsersDomain
import com.picpay.desafio.android.users.domain.repository.UsersRepository
import com.picpay.desafio.android.users.presentation.usecase.UserUseCase

class UserUseCaseImpl(
    private val repository: UsersRepository
) : UserUseCase {
    override suspend operator fun invoke(): List<UsersDomain> {
        return repository.getUsers()
    }
}