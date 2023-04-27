package com.picpay.desafio.android.di

import com.picpay.desafio.android.data.datasource.UsersDataSource
import com.picpay.desafio.android.data.datasource.UsersDataSourceImpl
import com.picpay.desafio.android.data.repository.UsersRepositoryImpl
import com.picpay.desafio.android.domain.repository.UsersRepository
import com.picpay.desafio.android.domain.usecase.UserUseCaseImpl
import com.picpay.desafio.android.presentation.usecase.UserUseCase
import com.picpay.desafio.android.presentation.viewModel.UserListViewModel
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
                dataSource = get(),
            )
        }
    }

    override val domainModule = module {
        factory<UserUseCase> { UserUseCaseImpl(repository = get()) }
    }

    override val presentationModule = module {
        viewModel {
            UserListViewModel(
                useCase = get()
            )
        }
    }
}