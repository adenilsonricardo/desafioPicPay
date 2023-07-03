package com.picpay.desafio.android.data.datasource

import com.picpay.desafio.android.UsersHelper
import com.picpay.desafio.android.users.data.api.PicPayService
import com.picpay.desafio.android.users.data.datasource.UsersDataSource
import com.picpay.desafio.android.users.data.datasource.UsersDataSourceImpl
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class UsersDataSourceImplTest {

    @MockK
    private var picPayService: PicPayService = mockk(relaxed = true)

    @MockK
    private var usersDataSource: UsersDataSource =
        UsersDataSourceImpl(picPayService)

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = StandardTestDispatcher()


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

    @Test
    fun `GIVEN service calls datasource WHEN datasource gets a response THEN return is a success`() {
        //GIVEN
        val expectedResult = UsersHelper.userResponse
        //WHEN
        coEvery { picPayService.getUsers() } returns expectedResult
        //THEN
        coEvery {
            usersDataSource.getUsers().collect { result ->
                assertEquals(expectedResult, result)
            }
        }
    }

    @Test
    fun `GIVEN service calls datasource WHEN datasource gets a response THEN return is a exception`() {
        //GIVEN
        val expected = Exception("Generic exception!!")
        //WHEN
        coEvery { picPayService.getUsers() } throws expected
        //THEN
        coEvery {
            usersDataSource.getUsers().catch { result ->
                assertEquals(expected, result)
            }
        }
    }
}