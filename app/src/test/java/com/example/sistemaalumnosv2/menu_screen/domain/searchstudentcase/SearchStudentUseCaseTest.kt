package com.example.sistemaalumnosv2.menu_screen.domain.searchstudentcase

import com.example.sistemaalumnosv2.menu_screen.data.model.DataStudent
import com.example.sistemaalumnosv2.menu_screen.data.network.searchstudent.SearchStudentRepository
import com.example.sistemaalumnosv2.vo.Resource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class SearchStudentUseCaseTest{

    private val dni = 99999999
    private val name = "exampleName"
    private val surname = "exampleSurname"
    private val year =  "4.1"
    private val firstTerm = 10
    private val secondTerm = 10
    private val thirdTerm = 10
    private val uid = "exampleUidAuth"

    @MockK
    private lateinit var searchStudentRepository: SearchStudentRepository

    private lateinit var searchStudentUseCase: SearchStudentUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        searchStudentUseCase = SearchStudentUseCase(searchStudentRepository)
    }

    @Test
    fun `search student use case return success`() = runBlocking {

        val dataStudent = mutableListOf(DataStudent(dni, name, surname, year, firstTerm, secondTerm, thirdTerm))
        coEvery { searchStudentRepository.searchStudent(uid) } returns Resource.Success(dataStudent)

        val response = searchStudentUseCase.searchStudent(uid)

        assertTrue(response is Resource.Success)
        assertEquals(dataStudent, (response as Resource.Success).data)
    }

    @Test
    fun `search student use case return loading`() = runBlocking {

        coEvery { searchStudentRepository.searchStudent(uid) } returns Resource.Loading()

        val response = searchStudentUseCase.searchStudent(uid)

        assertTrue(response is Resource.Loading)

    }

    @Test
    fun `search student use case return failure`() = runBlocking {

        coEvery { searchStudentRepository.searchStudent(uid) } returns Resource.Failure(Exception())

        val response = searchStudentUseCase.searchStudent(uid)

        assertTrue(response is Resource.Failure)

    }
}