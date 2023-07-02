package com.picpay.desafio.android.users.data.api

import com.picpay.desafio.android.users.data.models.User
import retrofit2.http.GET


interface PicPayService {

    @GET("users")
    suspend fun getUsers(): List<User>
}