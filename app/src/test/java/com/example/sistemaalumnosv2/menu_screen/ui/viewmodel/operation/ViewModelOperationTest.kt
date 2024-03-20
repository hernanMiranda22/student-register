package com.example.sistemaalumnosv2.menu_screen.ui.viewmodel.operation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.sistemaalumnosv2.menu_screen.data.model.DataStudent
import com.example.sistemaalumnosv2.menu_screen.domain.searchstudentcase.SearchStudentUseCase
import com.example.sistemaalumnosv2.vo.Resource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ViewModelOperationTest{
    @MockK
    private lateinit var searchStudentUseCase: SearchStudentUseCase

    private lateinit var viewModelOperation: ViewModelOperation

    private val dni = 99999999
    private val name = "exampleName"
    private val surname = "exampleSurname"
    private val year = "4.1"
    private val firstTerm = 0
    private val secondTerm= 0
    private val thirdTerm = 0
    private val uidUser = "uidExample"

    @get:Rule
    val rule : InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        viewModelOperation = ViewModelOperation(searchStudentUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

    @Test
    fun `search data student return success`() = runTest {
        val dataModel = mutableListOf(DataStudent(dni, name, surname, year, firstTerm, secondTerm, thirdTerm))
        coEvery { searchStudentUseCase.searchStudent(uidUser) } returns Resource.Success(dataModel)

        viewModelOperation.searchDataStudent(uidUser)

        assert(viewModelOperation.studentDataModel.value == Resource.Success(dataModel))
    }

    @Test
    fun `search data student return failure`() = runTest {
        val exception = Exception()
        coEvery { searchStudentUseCase.searchStudent(uidUser) } returns Resource.Failure(exception)

        viewModelOperation.searchDataStudent(uidUser)

        assert(viewModelOperation.exception.value == exception)
    }
}