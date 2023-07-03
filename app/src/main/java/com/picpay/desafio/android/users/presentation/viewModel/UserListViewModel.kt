package com.picpay.desafio.android.users.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.users.presentation.state.UserState
import com.picpay.desafio.android.users.presentation.usecase.UserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

private const val IS_LOADING = true

class UserListViewModel(
    private val useCase: UserUseCase,
) : ViewModel() {

    val listUsers = MutableStateFlow<UserState>(UserState.Inactive)

    fun fetchUsers() {
        viewModelScope.launch {
            listUsers.value = UserState.Loading(isLoading = IS_LOADING)
            listUsers.value = try {
                UserState.ResponseData(useCase.invoke())
            } catch (e: Exception) {
                UserState.Error(e.localizedMessage)
            }
        }
    }
}