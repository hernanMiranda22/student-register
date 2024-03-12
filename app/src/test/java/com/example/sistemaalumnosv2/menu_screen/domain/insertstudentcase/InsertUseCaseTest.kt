package com.example.sistemaalumnosv2.menu_screen.domain.insertstudentcase

import com.example.sistemaalumnosv2.menu_screen.data.model.DataStudent
import com.example.sistemaalumnosv2.menu_screen.data.network.insertstudent.InsertStudentRepository
import com.example.sistemaalumnosv2.vo.Resource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class InsertUseCaseTest{

    private val dni = 99999999
    private val name = "exampleName"
    private val surname = "exampleSurname"
    private val year = "4.1"
    private val firstTerm = 0
    private val secondTerm = 0
    private val thirdTerm = 0
    private val idUser = "uidUserAuth"

    @MockK
    private lateinit var insertStudentRepository : InsertStudentRepository

    private lateinit var insertUseCase: InsertUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        insertUseCase = InsertUseCase(insertStudentRepository)
    }

    @Test
    fun `insert student use case return success`() = runBlocking {

        val dataStudent = DataStudent(dni, name, surname, year, firstTerm, secondTerm, thirdTerm)
        coEvery { insertStudentRepository.insertStudent(dni, name, surname, year, idUser) } returns Resource.Success(dataStudent)

        val response = insertUseCase.insertStudent(dni, name, surname, year, idUser)

        assertTrue(response is Resource.Success)
        assertEquals(dataStudent, (response as Resource.Success).data)

    }

    @Test
    fun `insert student use case return loading`() = runBlocking {

        coEvery { insertStudentRepository.insertStudent(dni, name, surname, year, idUser) } returns Resource.Loading()

        val response = insertUseCase.insertStudent(dni, name, surname, year, idUser)

        assertTrue(response is Resource.Loading)

    }

    @Test
    fun `insert student use case return failure`() = runBlocking {

        coEvery { insertStudentRepository.insertStudent(dni, name, surname, year, idUser) } returns Resource.Failure(Exception())

        val response = insertUseCase.insertStudent(dni, name, surname, year, idUser)

        assertTrue(response is Resource.Failure)

    }
}