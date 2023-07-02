package com.picpay.desafio.android.users.data.mapper

import com.picpay.desafio.android.users.data.models.User
import com.picpay.desafio.android.users.domain.models.UsersDomain

interface UsersListMapper {
    suspend fun map(source: User): UsersDomain
}