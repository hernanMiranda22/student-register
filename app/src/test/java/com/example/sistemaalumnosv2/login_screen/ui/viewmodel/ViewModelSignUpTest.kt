package com.example.sistemaalumnosv2.login_screen.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.sistemaalumnosv2.login_screen.domain.signupcase.SignUpUserCase
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
class ViewModelSignUpTest{
    @MockK
    private lateinit var signUpUserUseCase: SignUpUserCase

    private lateinit var viewModelSignUp: ViewModelSignUp

    @get:Rule
    val rule : InstantTaskExecutorRule = InstantTaskExecutorRule()

    private val email = "example@gmail.com"
    private val password = "123456"

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        viewModelSignUp = ViewModelSignUp(signUpUserUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

    @Test
    fun `sign up new user return success`() = runTest{
        val userModel = com.example.sistemaalumnosv2.login_screen.data.model.User(
            email,
            password
        )
        coEvery { signUpUserUseCase.signUpEmailAndPassword(email, password) } returns Resource.Success(userModel)

        viewModelSignUp.signUpNewUser(email, password)

        assert(viewModelSignUp.userModel.value == Resource.Success(userModel))
    }

    @Test
    fun `sign up new user return failure`() = runTest{
        val exception = Exception()
        coEvery { signUpUserUseCase.signUpEmailAndPassword(email, password) } returns Resource.Failure(exception)

        viewModelSignUp.signUpNewUser(email, password)

        assert(viewModelSignUp.userException.value == exception)
    }
}