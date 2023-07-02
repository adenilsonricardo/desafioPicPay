package com.picpay.desafio.android.presentation.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.picpay.desafio.android.UsersHelper
import com.picpay.desafio.android.users.data.models.User
import com.picpay.desafio.android.users.presentation.usecase.UserUseCase
import com.picpay.desafio.android.users.presentation.viewModel.UserListViewModel
import io.mockk.MockKAnnotations.init
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

class UserListViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: UserListViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = StandardTestDispatcher()

    @MockK
    lateinit var useCase: UserUseCase

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        init(this)
        viewModel = UserListViewModel(useCase)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `GIVEN viewmodel makes the usecase call WHEN usecase returns the request THEN answer is success`() {
        //GIVEN
        val expectedResult = UsersHelper.userResponse
        //WHEN
        coEvery {
            useCase.invoke()
        } returns flow {
            emit(expectedResult)
        }
        //THEN
        viewModel.getUsers()
        viewModel.users.observeForever { result ->
            assertEquals(expectedResult, result)
        }
    }

    @Test
    fun `GIVEN viewmodel makes the usecase call WHEN usecase returns the request THEN answer is failure`() {
        //WHEN
        val expectedResult = UsersHelper.userResponseError
        coEvery {
            useCase.invoke()
        } returns flow { getResponseError() }
        //THEN
        viewModel.getUsers()
        viewModel.error.observeForever {
            assertEquals(getResponseError(), expectedResult)
        }
    }

    private fun getResponseError(): Response<List<User>> {
        return Response.error(401, ResponseBody.create("application/json".toMediaType(), ""))
    }
}