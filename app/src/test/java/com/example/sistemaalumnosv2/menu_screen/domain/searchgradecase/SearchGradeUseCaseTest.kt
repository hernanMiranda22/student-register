package com.example.sistemaalumnosv2.menu_screen.domain.searchgradecase

import com.example.sistemaalumnosv2.menu_screen.data.model.GradeStudent
import com.example.sistemaalumnosv2.menu_screen.data.network.searchgrade.SearchGradeRepository
import com.example.sistemaalumnosv2.vo.Resource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class SearchGradeUseCaseTest{

    private val name = "exampleName"
    private val surname = "exampleSurname"
    private val year =  "4.1"
    private val firstTerm = 10
    private val secondTerm = 10
    private val thirdTerm = 10

    private val dni = 99999999
    private val uid = "exampleUidAuth"


    @MockK
    private lateinit var searchGradeRepository: SearchGradeRepository

    private lateinit var searchGradeUseCase: SearchGradeUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        searchGradeUseCase = SearchGradeUseCase(searchGradeRepository)
    }

    @Test
    fun `search grade use case return success`() = runBlocking {

        val gradeStudent = mutableListOf(GradeStudent(name, surname, year, firstTerm, secondTerm, thirdTerm))
        coEvery { searchGradeRepository.getGradeStudent(dni, uid) } returns Resource.Success(gradeStudent)

        val response = searchGradeUseCase.getGradeStudent(dni, uid)

        assertTrue(response is Resource.Success)
        assertEquals(gradeStudent, (response as Resource.Success).data)
    }


    @Test
    fun `search grade use case return failure`() = runBlocking {


        coEvery { searchGradeRepository.getGradeStudent(dni, uid) } returns Resource.Failure(Exception())

        val response = searchGradeUseCase.getGradeStudent(dni, uid)

        assertTrue(response is Resource.Failure)

    }
}