package com.example.sistemaalumnosv2.menu_screen.domain.insertgradecase

import com.example.sistemaalumnosv2.menu_screen.data.model.TermData
import com.example.sistemaalumnosv2.menu_screen.data.network.insertgrade.GradeStudentRepository
import com.example.sistemaalumnosv2.vo.Resource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GradeStudentUseCaseTest{

    private val dni = 99999999
    private val firstTerm = 10
    private val secondTerm = 10
    private val thirdTerm = 10
    private val uid = "exampleUidAuth"

    @MockK
    private lateinit var gradeStudentRepository: GradeStudentRepository

    private lateinit var gradeStudentUseCase: GradeStudentUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        gradeStudentUseCase = GradeStudentUseCase(gradeStudentRepository)
    }

    @Test
    fun `use case grade student return success`()= runBlocking {
        //Given
        val termData = TermData(firstTerm, secondTerm, thirdTerm)
        coEvery { gradeStudentRepository.insertGrade(dni, firstTerm, secondTerm, thirdTerm, uid) } returns Resource.Success(termData)

        //When
        val response = gradeStudentUseCase.insertGrade(dni, firstTerm, secondTerm, thirdTerm, uid)

        //Then
        assertTrue(response is Resource.Success)
        assertEquals(termData, (response as Resource.Success).data)
    }

    @Test
    fun `use case grade student return loading`()= runBlocking {
        //Given
        coEvery { gradeStudentRepository.insertGrade(dni, firstTerm, secondTerm, thirdTerm, uid) } returns Resource.Loading()

        //When
        val response = gradeStudentUseCase.insertGrade(dni, firstTerm, secondTerm, thirdTerm, uid)

        //Then
        assertTrue(response is Resource.Loading)
    }

    @Test
    fun `use case grade student return failure`()= runBlocking {
        //Given
        coEvery { gradeStudentRepository.insertGrade(dni, firstTerm, secondTerm, thirdTerm, uid) } returns Resource.Failure(Exception())

        //When
        val response = gradeStudentUseCase.insertGrade(dni, firstTerm, secondTerm, thirdTerm, uid)

        //Then
        assertTrue(response is Resource.Failure)
    }
}