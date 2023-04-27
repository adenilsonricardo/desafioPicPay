package com.picpay.desafio.android.presentation.usecase

import com.picpay.desafio.android.data.models.User
import kotlinx.coroutines.flow.Flow

interface UserUseCase {
    suspend operator fun invoke(): Flow<List<User>>
}