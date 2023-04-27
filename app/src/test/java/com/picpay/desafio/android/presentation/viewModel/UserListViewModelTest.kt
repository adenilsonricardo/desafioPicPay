package com.picpay.desafio.android.presentation.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.picpay.desafio.android.UsersHelper
import com.picpay.desafio.android.data.models.User
import com.picpay.desafio.android.presentation.usecase.UserUseCase
import io.mockk.MockKAnnotations.init
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
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

    private val testDispatcher = TestCoroutineDispatcher()

    @MockK
    lateinit var useCase: UserUseCase

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        init(this)
        viewModel = UserListViewModel(useCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `view model should call the useCase and get a successful response`() = runBlockingTest {
        val expectedResult = UsersHelper.userResponse
        coEvery {
            useCase.invoke()
        } returns flow {
            emit(expectedResult)
        }
        viewModel.getUsers()

        viewModel.users.observeForever { result ->
            assertEquals(expectedResult, result)
        }


    }

    @Test
    fun `view model should call the useCase and get a thwo response`() = runBlockingTest {
        coEvery {
            useCase.invoke()
        } returns flow { getResponseError() }
        viewModel.getUsers()

        viewModel.error.observeForever { result ->
            assertEquals(getResponseError(), result)
        }

    }

    //arrumar
    private fun getResponseError(): Response<List<User>> {
        return Response.error(401, ResponseBody.create("application/json".toMediaType(), ""))
    }
}