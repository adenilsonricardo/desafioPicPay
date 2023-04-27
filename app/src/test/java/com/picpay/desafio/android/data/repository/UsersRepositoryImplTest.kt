package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.UsersHelper
import com.picpay.desafio.android.data.api.PicPayService
import com.picpay.desafio.android.data.datasource.UsersDataSource
import com.picpay.desafio.android.data.datasource.UsersDataSourceImpl
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class UsersRepositoryImplTest {

    @MockK
    private var picPayService: PicPayService = mockk(relaxed = true)

    @MockK
    private var usersDataSource: UsersDataSource =
        UsersDataSourceImpl(picPayService)

    @MockK
    private var usersRepository = UsersRepositoryImpl(usersDataSource)

    private val testDispatcher = TestCoroutineDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        initMocks()
    }

    private fun initMocks() {
        MockKAnnotations.init(this)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `repository should call the dataSource and get a successful response`() = runBlockingTest {
        //GIVEN
        val expectedResult = UsersHelper.userResponse
        //WHEN
        coEvery { usersDataSource.getUsers() } returns flow { emit(expectedResult) }
        //THEN
        coEvery {
            usersRepository.getUsers().collect { result ->
                assertEquals(expectedResult, result)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `repository should call the dataSource and get a exception`() = runBlockingTest {
        //GIVEN
        val expected = Exception("Generic exception!!")
        //WHEN
        coEvery { usersDataSource.getUsers() } returns flow { throw expected }
        //THEN
        coEvery {
            usersRepository.getUsers().collect { result ->
                assertEquals(expected, result)
            }
        }
    }
}