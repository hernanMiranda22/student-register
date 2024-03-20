package com.example.sistemaalumnosv2.menu_screen.ui.viewmodel.grade

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.sistemaalumnosv2.menu_screen.data.model.GradeStudent
import com.example.sistemaalumnosv2.menu_screen.data.model.TermData
import com.example.sistemaalumnosv2.menu_screen.domain.insertgradecase.GradeStudentUseCase
import com.example.sistemaalumnosv2.menu_screen.domain.searchgradecase.SearchGradeUseCase
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
class ViewModelGradeTest{
    @MockK
    private lateinit var searchGradeUseCase: SearchGradeUseCase

    @MockK
    private lateinit var gradeStudentUseCase: GradeStudentUseCase

    private lateinit var viewModelGrade: ViewModelGrade

    @get:Rule
    val rule : InstantTaskExecutorRule = InstantTaskExecutorRule()

    private val name = "exampleName"
    private val surname = "exampleSurname"
    private val year = "4.1"
    private val firstTerm = 0
    private val secondTerm = 0
    private val thirdTerm = 0

    private val dni = 99999999
    private val uid = "exampleUid"

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        viewModelGrade = ViewModelGrade(searchGradeUseCase, gradeStudentUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

    @Test
    fun `when the user trying to get student data, getDataAndTerm return success`() = runTest {
        val mutableDataStudent = mutableListOf(GradeStudent(name, surname, year, firstTerm, secondTerm, thirdTerm))
        coEvery { searchGradeUseCase.getGradeStudent(dni, uid) } returns Resource.Success(mutableDataStudent)

        viewModelGrade.getDataAndTerm(dni, uid)

        assert(viewModelGrade.gradeStudentModel.value == Resource.Success(mutableDataStudent))
    }

    @Test
    fun `when the user trying to get student data, getDataAndTerm return failure`() = runTest {
        val exception = Exception()
        coEvery { searchGradeUseCase.getGradeStudent(dni, uid) } returns Resource.Failure(exception)

        viewModelGrade.getDataAndTerm(dni, uid)

        assert(viewModelGrade.exception.value == exception)
    }

    @Test
    fun `when the user insert term student, insertGrade return success`() = runTest {
        val termData = TermData(firstTerm, secondTerm, thirdTerm)
        coEvery { gradeStudentUseCase.insertGrade(dni, firstTerm, secondTerm, thirdTerm, uid) } returns Resource.Success(termData)

        viewModelGrade.insertGrade(dni, firstTerm, secondTerm, thirdTerm, uid)

        assert(viewModelGrade.termDataModel.value == Resource.Success(termData))
    }

    @Test
    fun `when the user insert term student, insertGrade return failure`() = runTest {
        val exception = Exception()
        coEvery { gradeStudentUseCase.insertGrade(dni, firstTerm, secondTerm, thirdTerm, uid) } returns Resource.Failure(exception)

        viewModelGrade.insertGrade(dni, firstTerm, secondTerm, thirdTerm, uid)

        assert(viewModelGrade.exception.value == exception)
    }
}
