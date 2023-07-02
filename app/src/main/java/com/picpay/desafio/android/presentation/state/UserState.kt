package com.picpay.desafio.android.presentation.state

import com.picpay.desafio.android.data.models.User

sealed class UserState {
    object Inactive : UserState()
    data class Loading(val isLoading: Boolean) : UserState()
    data class ResponseData(val users: List<User>?) : UserState()
    data class Error(val error: String?) : UserState()
}