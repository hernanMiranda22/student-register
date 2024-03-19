package com.example.sistemaalumnosv2.login_screen.domain.signupcase

import com.example.sistemaalumnosv2.login_screen.data.model.User
import com.example.sistemaalumnosv2.login_screen.data.network.signupemail.SignUpUserRepository
import com.example.sistemaalumnosv2.vo.Resource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class SignUpUserCaseTest{
    @RelaxedMockK
    private lateinit var signUpUserRepository : SignUpUserRepository

    private lateinit var signUpUserCase: SignUpUserCase

    @Before
    fun onBefore(){
       MockKAnnotations.init(this)
        signUpUserCase = SignUpUserCase(signUpUserRepository)
    }

    @Test
    fun `signup use case return success`() = runBlocking {
        //Given
        val email = "jorge123@gmail.com"
        val password = "123456"
        val user = User(email, password)

        coEvery { signUpUserRepository.signUpEmailAndPassword(email, password) } returns Resource.Success(user)

        //When
        val response = signUpUserCase.signUpEmailAndPassword(email,password)

        //Then
        assertTrue(response is Resource.Success)
        assertEquals(user, (response as Resource.Success).data)
    }

    @Test
    fun `signup use case return failure`() = runBlocking {
        //Given
        val email = "jorge123@gmail.com"
        val password = "123456"
        coEvery { signUpUserRepository.signUpEmailAndPassword(email, password) } returns Resource.Failure(Exception())

        //When
        val response = signUpUserCase.signUpEmailAndPassword(email, password)

        //Then
        assertTrue(response is  Resource.Failure)
    }
}