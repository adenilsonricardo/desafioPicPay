package com.picpay.desafio.android.users.presentation.state

import com.picpay.desafio.android.users.domain.models.UsersDomain

sealed class UserState {
    object Inactive : UserState()
    data class Loading(override val isLoading: Boolean) : UserState()
    data class ResponseData(val users: List<UsersDomain>?) : UserState()
    data class Error(val error: String?) : UserState()

    open val isLoading: Boolean = true
}