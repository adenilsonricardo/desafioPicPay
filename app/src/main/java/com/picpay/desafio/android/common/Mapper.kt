package com.picpay.desafio.android.common

interface Mapper<S, T> {
    fun map(source: S): T
}