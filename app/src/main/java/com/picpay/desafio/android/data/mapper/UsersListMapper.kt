package com.picpay.desafio.android.data.mapper

import com.picpay.desafio.android.data.models.User
import com.picpay.desafio.android.domain.models.UsersDomain

interface UsersListMapper {
    suspend fun map(source: User): UsersDomain
}