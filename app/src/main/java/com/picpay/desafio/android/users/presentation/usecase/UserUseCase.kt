package com.picpay.desafio.android.users.presentation.usecase

import com.picpay.desafio.android.users.domain.models.UsersDomain

interface UserUseCase {
    suspend operator fun invoke(): List<UsersDomain>
}