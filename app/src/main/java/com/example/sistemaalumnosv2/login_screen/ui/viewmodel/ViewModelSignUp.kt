package com.example.sistemaalumnosv2.login_screen.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.sistemaalumnosv2.login_screen.data.model.User
import com.example.sistemaalumnosv2.login_screen.domain.signupcase.SignUpUserCase
import com.example.sistemaalumnosv2.vo.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelSignUp @Inject constructor(private val signUpUserUseCase: SignUpUserCase):ViewModel() {

    private val _userModel = MutableLiveData<Resource<User>>()
    val userModel :LiveData<Resource<User>>
        get() = _userModel

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean>
        get() = _isLoading

    private val _userException = MutableLiveData<Exception>()
    val userException :LiveData<Exception>
        get() = _userException

    fun signUpNewUser(email : String, password : String){
        viewModelScope.launch {

            when(val result = signUpUserUseCase.signUpEmailAndPassword(email, password)){
                is Resource.Loading -> {
                    _isLoading.postValue(true)
                }
                is Resource.Success -> {
                    _userModel.postValue(result)
                    _isLoading.postValue(false)
                }
                is Resource.Failure -> {
                    _userException.postValue(result.exception)
                }
            }
        }
    }
//    private val dispatchers = Dispatchers.IO

//    fun signUpNewUser(email : String, password : String) = liveData(dispatchers) {
//        emit(Resource.Loading())
//
//        try {
//
//            val signUp = signUpUserUseCase.signUpEmailAndPassword(email, password)
//            emit(Resource.Success(signUp))
//
//        }catch (e: Exception){
//            emit(Resource.Failure(e))
//        }
//    }
}