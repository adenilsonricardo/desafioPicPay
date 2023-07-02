package com.picpay.desafio.android.data.mapper

import com.picpay.desafio.android.common.Mapper
import com.picpay.desafio.android.data.models.User
import com.picpay.desafio.android.domain.models.UsersDomain

class UsersListMapper : Mapper<User, UsersDomain> {
    override fun map(source: User): UsersDomain {
        return UsersDomain(
            id = source.id,
            name = source.name,
            username = source.username,
            img = source.img
        )
    }
}