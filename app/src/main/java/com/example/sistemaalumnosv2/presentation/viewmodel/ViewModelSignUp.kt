package com.example.sistemaalumnosv2.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.sistemaalumnosv2.domain.SignUpUserUseCase
import com.example.sistemaalumnosv2.vo.Resource
import kotlinx.coroutines.Dispatchers

class ViewModelSignUp(private val signUpUserUseCase: SignUpUserUseCase):ViewModel() {

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