package com.picpay.desafio.android.users.di

import com.picpay.desafio.android.common.mapper.UsersListMapperImpl
import com.picpay.desafio.android.common.network.RetrofitService
import com.picpay.desafio.android.users.data.api.PicPayService
import com.picpay.desafio.android.users.data.datasource.UsersDataSource
import com.picpay.desafio.android.users.data.datasource.UsersDataSourceImpl
import com.picpay.desafio.android.users.data.mapper.UsersListMapper
import com.picpay.desafio.android.users.data.repository.UsersRepositoryImpl
import com.picpay.desafio.android.users.domain.repository.UsersRepository
import com.picpay.desafio.android.users.domain.usecase.UserUseCaseImpl
import com.picpay.desafio.android.users.presentation.usecase.UserUseCase
import com.picpay.desafio.android.users.presentation.viewModel.UserListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object UsersModule {

    val usersModule = module {
        single { RetrofitService.retrofit.create(PicPayService::class.java) }
        factory<UsersDataSource> { UsersDataSourceImpl(service = get()) }
        factory<UsersRepository> { UsersRepositoryImpl(dataSource = get(), mapper = get()) }
        factory<UsersListMapper> { UsersListMapperImpl() }

        factory<UserUseCase> { UserUseCaseImpl(repository = get()) }

        viewModel { UserListViewModel(useCase = get()) }
    }
}