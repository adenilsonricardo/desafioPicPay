package com.picpay.desafio.android.data.datasource

import com.picpay.desafio.android.UsersHelper
import com.picpay.desafio.android.data.api.PicPayService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
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
    fun `dataSource should call the dataSource and get a successful response`() = runBlockingTest {
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

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `usersDataSource should call the dataSource and get a exception`() = runBlockingTest {
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