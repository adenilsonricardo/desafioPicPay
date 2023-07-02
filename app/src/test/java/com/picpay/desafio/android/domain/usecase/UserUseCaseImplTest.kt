package com.picpay.desafio.android.domain.usecase

import com.picpay.desafio.android.UsersHelper
import com.picpay.desafio.android.users.data.datasource.UsersDataSource
import com.picpay.desafio.android.users.data.repository.UsersRepositoryImpl
import com.picpay.desafio.android.users.presentation.usecase.UserUseCase
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import org.junit.Assert.assertEquals
import org.junit.Test

class UserUseCaseImplTest {

    @MockK
    private val useCase: UserUseCase = mockk(relaxed = true)
    private val dataSource = mockk<UsersDataSource>(relaxed = true)

    private val repository by lazy {
        UsersRepositoryImpl(dataSource)
    }

    @Test
    fun `GIVEN request to usecase WHEN invoke send request to repository THEN returns success`() {
        //GIVEN
        val expectedResult = UsersHelper.userResponse
        //WHEN
        coEvery { repository.getUsers() } returns flow { emit(expectedResult) }
        //THEN
        coEvery {
            useCase.invoke().collect() { result ->
                assertEquals(expectedResult, result)
            }
        }
    }

    @Test
    fun `GIVEN request to usecase WHEN invoke send request to repository THEN returns failure`() {
        //GIVEN
        val expectedResult = Exception("Generic message exception!!!")
        //WHEN
        coEvery { repository.getUsers() } throws expectedResult
        //THEN
        coEvery {
            useCase.invoke().catch { result ->
                assertEquals(expectedResult, result)
            }
        }
    }
}