package com.picpay.desafio.android.common.di

import android.app.Application
import com.picpay.desafio.android.users.di.UsersModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(appDeclaration = KoinAppDeclarationProvider.get(this))
    }

    object KoinAppDeclarationProvider {
        fun get(application: Application): KoinAppDeclaration = {
            androidContext(application)
            UsersModule().load()
        }
    }
}