package com.example.sistemaalumnosv2.login_screen.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sistemaalumnosv2.login_screen.data.model.User
import com.example.sistemaalumnosv2.login_screen.domain.signincase.SignInUseCase
import com.example.sistemaalumnosv2.vo.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelSignIn @Inject constructor(private val signInUseCase: SignInUseCase):ViewModel() {


    private val _userModel = MutableLiveData<Resource<User>>()
    val userModel : LiveData<Resource<User>>
        get() = _userModel

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean>
        get() = _isLoading

    private val _userException = MutableLiveData<Exception>()
    val userException :LiveData<Exception>
        get() = _userException


    fun signInWithEmail(email: String, password : String){
        viewModelScope.launch {

            _isLoading.postValue(true)
            when(val result = signInUseCase.signInWithEmail(email, password)){
                is Resource.Success -> {
                    _userModel.postValue(result)
                }
                is Resource.Failure -> {
                    _userException.postValue(result.exception)
                }
            }
            _isLoading.postValue(false)
        }
    }

}