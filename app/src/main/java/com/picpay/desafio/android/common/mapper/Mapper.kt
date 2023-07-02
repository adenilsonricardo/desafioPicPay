package com.picpay.desafio.android.common.mapper

interface Mapper<S, T> {
    suspend fun map(source: S): T
}