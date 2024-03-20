package com.example.sistemaalumnosv2.menu_screen.ui.viewmodel.studentviewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.sistemaalumnosv2.menu_screen.data.model.DataStudent
import com.example.sistemaalumnosv2.menu_screen.domain.insertstudentcase.InsertUseCase
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
class ViewModelStudentTest{
    @MockK
    private lateinit var insertUseCase: InsertUseCase

    private lateinit var viewModelStudent: ViewModelStudent

    @get:Rule
    val rule : InstantTaskExecutorRule = InstantTaskExecutorRule()

    private val dni = 99999999
    private val name = "exampleName"
    private val surname = "exampleSurname"
    private val year =  "4.1"
    private val firstTerm = 10
    private val secondTerm = 10
    private val thirdTerm = 10
    private val uid = "exampleUidAuth"


    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        viewModelStudent = ViewModelStudent(insertUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

    @Test
    fun `insert new student return success` () = runTest {
        val dataModel = DataStudent(dni, name, surname, year, firstTerm, secondTerm, thirdTerm)
        coEvery { insertUseCase.insertStudent(dni,name,surname,year,uid) } returns Resource.Success(dataModel)

        viewModelStudent.insertNewStudent(dni,name,surname,year,uid)

        assert(viewModelStudent.studentModel.value == Resource.Success(dataModel))
    }

    @Test
    fun `insert new student return failure` () = runTest {
        val exception = Exception()
        coEvery { insertUseCase.insertStudent(dni,name,surname,year,uid) } returns Resource.Failure(exception)

        viewModelStudent.insertNewStudent(dni,name,surname,year,uid)

        assert(viewModelStudent.exception.value == exception)
    }
}