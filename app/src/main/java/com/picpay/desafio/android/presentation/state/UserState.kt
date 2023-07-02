package com.picpay.desafio.android.presentation.state

import com.picpay.desafio.android.domain.models.UsersDomain

sealed class UserState {
    object Inactive : UserState()
    data class Loading(val isLoading: Boolean) : UserState()
    data class ResponseData(val users: List<UsersDomain>?) : UserState()
    data class Error(val error: String?) : UserState()
}