package com.example.sistemaalumnosv2.presentation.viewmodel.signinviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.sistemaalumnosv2.domain.signincase.SignInUseCase
import com.example.sistemaalumnosv2.domain.signingooglecase.SignInGoogleUseCase
import com.example.sistemaalumnosv2.vo.Resource
import com.google.firebase.auth.AuthCredential
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class ViewModelSignIn @Inject constructor(private val signInUseCase: SignInUseCase):ViewModel() {

    private val dispatchers = Dispatchers.IO
    fun signInWithEmail(email: String, password : String) = liveData(dispatchers){
        emit(Resource.Loading())

        try {

            val signInUser = signInUseCase.signInWithEmail(email, password)
            emit(Resource.Success(signInUser))

        }catch (e : Exception){
            emit(Resource.Failure(e))
        }
    }


//    fun signInUserWithGoogle(credential: AuthCredential) = liveData(dispatchers) {
//        emit(Resource.Loading())
//
//        try {
//
//            val userSignIn = signInGoogleUseCase.signInWithGoogle(credential)
//            emit(Resource.Success(userSignIn))
//
//        }catch (e:Exception){
//            emit(Resource.Failure(e))
//        }
//    }
}