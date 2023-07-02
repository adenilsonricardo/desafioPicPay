package com.picpay.desafio.android

import com.picpay.desafio.android.users.data.models.User

object UsersHelper {

    val userResponse = listOf(
        User(
            id = "1234",
            name = "Tadeu",
            username = "@TadeuBernardes",
            img = ""
        )
    )

    val userResponseError = listOf(
        User(
            id = "",
            name = "",
            username = "",
            img = ""
        )
    )
}