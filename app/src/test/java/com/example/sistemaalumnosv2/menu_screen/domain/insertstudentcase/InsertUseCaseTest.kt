package com.example.sistemaalumnosv2.menu_screen.domain.insertstudentcase

import com.example.sistemaalumnosv2.menu_screen.data.ResourceMenu
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
        coEvery { insertStudentRepository.insertStudent(dni, name, surname, year, idUser) } returns ResourceMenu.Success(dataStudent)

        val response = insertUseCase.insertStudent(dni, name, surname, year, idUser)

        assertTrue(response is ResourceMenu.Success)
        assertEquals(dataStudent, (response as ResourceMenu.Success).data)

    }

    @Test
    fun `insert student use case return failure`() = runBlocking {

        coEvery { insertStudentRepository.insertStudent(dni, name, surname, year, idUser) } returns ResourceMenu.Failure(Exception())

        val response = insertUseCase.insertStudent(dni, name, surname, year, idUser)

        assertTrue(response is ResourceMenu.Failure)

    }
}