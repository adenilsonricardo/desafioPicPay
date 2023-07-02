package com.picpay.desafio.android.domain.usecase

import com.picpay.desafio.android.domain.models.UsersDomain
import com.picpay.desafio.android.domain.repository.UsersRepository
import com.picpay.desafio.android.presentation.usecase.UserUseCase

class UserUseCaseImpl(
    private val repository: UsersRepository
) : UserUseCase {
    override suspend operator fun invoke(): List<UsersDomain> {
        return repository.getUsers()
    }
}