package com.picpay.desafio.android.di

import com.picpay.desafio.android.data.datasource.UsersDataSource
import com.picpay.desafio.android.data.datasource.UsersDataSourceImpl
import com.picpay.desafio.android.data.mapper.UsersListMapper
import com.picpay.desafio.android.data.mapper.UsersListMapperImpl
import com.picpay.desafio.android.data.network.RetrofitService
import com.picpay.desafio.android.data.repository.UsersRepositoryImpl
import com.picpay.desafio.android.domain.repository.UsersRepository
import com.picpay.desafio.android.domain.usecase.UserUseCaseImpl
import com.picpay.desafio.android.presentation.usecase.UserUseCase
import com.picpay.desafio.android.presentation.viewModel.UserListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal class UsersModule : FeatureModule() {

    override val dataModule = module {
        single { RetrofitService.service }
        factory<UsersDataSource> { UsersDataSourceImpl(service = get()) }
        factory<UsersRepository> { UsersRepositoryImpl(dataSource = get(), mapper = get()) }
        factory<UsersListMapper> { UsersListMapperImpl() }
    }

    override val domainModule = module {
        factory<UserUseCase> { UserUseCaseImpl(repository = get()) }
    }

    override val presentationModule = module {
        viewModel { UserListViewModel(useCase = get()) }
    }
}