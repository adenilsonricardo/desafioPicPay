package com.picpay.desafio.android.utils

import com.google.gson.GsonBuilder
import com.picpay.desafio.android.data.api.PicPayService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"

class RetrofitService {
    companion object {
        val service: PicPayService

        init {
            val gson = GsonBuilder().setLenient().create()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
            service = retrofit.create(PicPayService::class.java)
        }
    }
}