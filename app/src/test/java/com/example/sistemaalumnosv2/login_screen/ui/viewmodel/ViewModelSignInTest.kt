package com.example.sistemaalumnosv2.login_screen.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.sistemaalumnosv2.login_screen.data.model.User
import com.example.sistemaalumnosv2.login_screen.domain.signincase.SignInUseCase
import com.example.sistemaalumnosv2.vo.Resource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import junit.framework.TestCase.assertEquals
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
class ViewModelSignInTest{

    @RelaxedMockK
    private lateinit var signInUseCase: SignInUseCase

    private lateinit var viewModelSignIn: ViewModelSignIn

    @get:Rule
    val rule : InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        viewModelSignIn = ViewModelSignIn(signInUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

    @Test
    fun `When the user is trying to access their account, the function return success`() = runTest {
        val email = "exampleEmail@gmail.com"
        val password = "123456"
        val user = User(email = email, password = password)

        coEvery { signInUseCase.signInWithEmail(email, password) } returns Resource.Success(user)

        viewModelSignIn.signInWithEmail(email, password)

        assert(viewModelSignIn.userModel.value == Resource.Success(user))
    }

    @Test
    fun `When the user is trying to access their account, the function return loading`() = runTest {
        val email = "exampleEmail@gmail.com"
        val password = "123456"

        coEvery { signInUseCase.signInWithEmail(email, password) } returns Resource.Loading()

        viewModelSignIn.signInWithEmail(email, password)

        assert(viewModelSignIn.isLoading.value == true)
    }

    @Test
    fun `When the user is trying to access their account, the function return failure`() = runTest {
        val email = "exampleEmail@gmail.com"
        val password = "123456"
        val exception = Exception()
        coEvery { signInUseCase.signInWithEmail(email, password) } returns Resource.Failure(
            exception
        )

        viewModelSignIn.signInWithEmail(email, password)

        assert(viewModelSignIn.userException.value == exception)
    }
}