package com.example.sistemaalumnosv2.menu_screen.ui.viewmodel.studentviewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sistemaalumnosv2.menu_screen.data.model.DataStudent
import com.example.sistemaalumnosv2.menu_screen.domain.insertstudentcase.InsertUseCase
import com.example.sistemaalumnosv2.vo.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelStudent @Inject constructor(private val insertUseCase: InsertUseCase):ViewModel() {

    private val _studentModel = MutableLiveData<Resource<DataStudent>>()
    val studentModel :LiveData<Resource<DataStudent>>
        get() = _studentModel

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean>
        get() = _isLoading

    private val _exception = MutableLiveData<Exception>()
    val userException :LiveData<Exception>
        get() = _exception

    fun insertNewStudent(dni:Int, name:String, surname:String, year:String, idUser: String){
        viewModelScope.launch {
            _isLoading.postValue(true)

            when(val result = insertUseCase.insertStudent(dni, name, surname, year, idUser)){
                is Resource.Success -> {
                    _studentModel.postValue(result)
                }
                is Resource.Failure -> {
                    _exception.postValue(result.exception)
                }
            }

            _isLoading.postValue(false)
        }
    }
}