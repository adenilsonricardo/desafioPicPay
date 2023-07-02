package com.picpay.desafio.android.presentation.usecase

import com.picpay.desafio.android.domain.models.UsersDomain

interface UserUseCase {
    suspend operator fun invoke(): List<UsersDomain>
}