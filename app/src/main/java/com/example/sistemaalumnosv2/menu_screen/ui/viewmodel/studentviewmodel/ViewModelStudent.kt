package com.example.sistemaalumnosv2.menu_screen.ui.viewmodel.studentviewmodel

import android.provider.ContactsContract.Data
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.sistemaalumnosv2.menu_screen.data.model.DataStudent
import com.example.sistemaalumnosv2.menu_screen.domain.insertstudentcase.InsertUseCase
import com.example.sistemaalumnosv2.menu_screen.ui.ResourceUIMenu
import com.example.sistemaalumnosv2.vo.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelStudent @Inject constructor(private val insertUseCase: InsertUseCase):ViewModel() {

    private val _studentModel = MutableLiveData<ResourceUIMenu<DataStudent>>()
    val studentModel :LiveData<ResourceUIMenu<DataStudent>>
        get() = _studentModel

    fun insertNewStudent(dni:Int, name:String, surname:String, year:String, idUser: String){
        viewModelScope.launch {
            try {
                _studentModel.postValue(ResourceUIMenu.Loading())

                val result = insertUseCase.insertStudent(dni, name, surname, year, idUser)

                _studentModel.postValue(ResourceUIMenu.Success(result) as ResourceUIMenu<DataStudent>)
            }catch (e :Exception){
                _studentModel.postValue(ResourceUIMenu.Failure(e))
            }
        }
    }
}