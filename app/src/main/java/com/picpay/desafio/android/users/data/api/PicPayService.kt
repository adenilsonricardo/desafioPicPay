package com.picpay.desafio.android.users.data.api

import com.picpay.desafio.android.common.utils.USERS_ENDPOINT
import com.picpay.desafio.android.users.data.models.User
import retrofit2.http.GET

interface PicPayService {

    @GET(USERS_ENDPOINT)
    suspend fun getUsers(): List<User>
}