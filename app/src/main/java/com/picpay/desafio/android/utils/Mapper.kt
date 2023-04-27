package com.picpay.desafio.android.utils

interface Mapper<S, T> {
    suspend fun map(source: S): T
}