package com.example.sistemaalumnosv2.login_screen.domain.signincase

import com.example.sistemaalumnosv2.login_screen.data.model.User
import com.example.sistemaalumnosv2.login_screen.data.network.sigin.SignInUserRepository
import com.example.sistemaalumnosv2.vo.Resource
import com.google.firebase.auth.FirebaseAuthException
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class SignInUseCaseTest{
    @RelaxedMockK
    private lateinit var signInUserRepository : SignInUserRepository

    private lateinit var signInUseCase: SignInUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        signInUseCase = SignInUseCase(signInUserRepository)
    }

    @Test
    fun `use case sign in returns success`() = runBlocking {
        //Given
        val email = "jorge@gmail.com"
        val password = "123456"
        val user = User(email, password)
        coEvery { signInUserRepository.signInWithEmail(email, password) } returns Resource.Success(user)

        //When
        val response = signInUseCase.signInWithEmail(email, password)

        //Then
        assertTrue(response is Resource.Success)
        assertEquals(user, (response as Resource.Success).data)
    }

    @Test
    fun `use case sign in returns loading`() = runBlocking {
        //Given
        val email = "jorge@gmail.com"
        val password = "123456"
        coEvery { signInUserRepository.signInWithEmail(email, password) } returns Resource.Loading()

        //When
        val response = signInUseCase.signInWithEmail(email, password)

        //Then
        assertTrue(response is Resource.Loading)
    }

    @Test
    fun `use case sign in returns failure`() = runBlocking {
        //Given
        val email = "jorge@gmail.com"
        val password = "123456"
        coEvery { signInUserRepository.signInWithEmail(email, password) } returns Resource.Failure(
            Exception()
        )

        //When
        val response = signInUseCase.signInWithEmail(email, password)

        //Then
        assertTrue(response is Resource.Failure)

    }
}