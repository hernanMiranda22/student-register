package com.example.sistemaalumnosv2.ui.viewmodel.signupviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.sistemaalumnosv2.domain.signupcase.SignUpUserUseCase
import com.example.sistemaalumnosv2.vo.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class ViewModelSignUp @Inject constructor(private val signUpUserUseCase: SignUpUserUseCase):ViewModel() {

    private val dispatchers = Dispatchers.IO

    fun signUpNewUser(email : String, password : String) = liveData(dispatchers) {
        emit(Resource.Loading())

        try {

            val signUp = signUpUserUseCase.signUpEmailAndPassword(email, password)
            emit(Resource.Success(signUp))

        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }
}