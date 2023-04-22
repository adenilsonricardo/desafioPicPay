package com.picpay.desafio.android.di.di

import com.example.desafioooo.di.FeatureModule
import com.picpay.desafio.android.datasource.UsersDataSource
import com.picpay.desafio.android.datasource.UsersDataSourceImpl
import com.picpay.desafio.android.domain.repository.UsersRepository
import com.picpay.desafio.android.domain.repository.UsersRepositoryImpl
import com.picpay.desafio.android.domain.usecase.UserUseCase
import com.picpay.desafio.android.presentation.viewModel.UsersViewModel
import com.picpay.desafio.android.utils.RetrofitService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal class UsersModule : FeatureModule() {

    override val dataModule = module {
        factory { RetrofitService.service }

        factory<UsersDataSource> {
            UsersDataSourceImpl(
                service = get()
            )
        }

        factory<UsersRepository> {
            UsersRepositoryImpl(
                dataSource = get()
            )
        }
    }

    override val domainModule = module {
        factory { UserUseCase(repository = get()) }
    }

    override val presentationModule = module {
        viewModel {
            UsersViewModel(
                useCase = get()
            )
        }
    }
}