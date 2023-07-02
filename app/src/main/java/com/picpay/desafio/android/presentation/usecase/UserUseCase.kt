package com.picpay.desafio.android.presentation.usecase

import com.picpay.desafio.android.data.models.User

interface UserUseCase {
    suspend operator fun invoke(): List<User>
}