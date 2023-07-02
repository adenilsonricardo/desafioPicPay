package com.picpay.desafio.android.common

interface Mapper<S, T> {
    suspend fun map(source: S): T
}