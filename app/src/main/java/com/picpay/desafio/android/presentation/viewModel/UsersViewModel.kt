package com.picpay.desafio.android.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.data.models.User
import com.picpay.desafio.android.presentation.usecase.UserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class UsersViewModel(private val useCase: UserUseCase) : ViewModel() {

    private val _users: MutableLiveData<List<User>> = MutableLiveData()
    var users: LiveData<List<User>> = _users

    private val _error: MutableLiveData<String> = MutableLiveData()
    var error: LiveData<String> = _error

    fun getUsers() {
        viewModelScope.launch {
            useCase.invoke()
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    _error.value = e.message
                }.collect {
                    _users.value = it
                }
        }
    }
}