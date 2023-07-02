package com.picpay.desafio.android.users.data.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("img")
    val img: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("username")
    val username: String?
)