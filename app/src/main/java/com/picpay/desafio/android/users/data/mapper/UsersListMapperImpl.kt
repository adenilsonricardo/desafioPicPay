package com.picpay.desafio.android.users.data.mapper

import com.picpay.desafio.android.common.mapper.Mapper
import com.picpay.desafio.android.users.data.models.User
import com.picpay.desafio.android.users.domain.models.UsersDomain

class UsersListMapperImpl : Mapper<User, UsersDomain>, UsersListMapper {
    override suspend fun map(source: User): UsersDomain {
        return UsersDomain(
            id = source.id,
            name = source.name,
            username = source.username,
            img = source.img
        )
    }
}