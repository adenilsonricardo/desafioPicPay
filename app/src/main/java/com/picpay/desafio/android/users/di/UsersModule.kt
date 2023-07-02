package com.picpay.desafio.android.users.di

import com.picpay.desafio.android.common.di.FeatureModule
import com.picpay.desafio.android.users.data.datasource.UsersDataSource
import com.picpay.desafio.android.users.data.datasource.UsersDataSourceImpl
import com.picpay.desafio.android.users.data.mapper.UsersListMapper
import com.picpay.desafio.android.users.data.mapper.UsersListMapperImpl
import com.picpay.desafio.android.users.data.network.RetrofitService
import com.picpay.desafio.android.users.data.repository.UsersRepositoryImpl
import com.picpay.desafio.android.users.domain.repository.UsersRepository
import com.picpay.desafio.android.users.domain.usecase.UserUseCaseImpl
import com.picpay.desafio.android.users.presentation.usecase.UserUseCase
import com.picpay.desafio.android.users.presentation.viewModel.UserListViewModel
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