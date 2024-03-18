package com.example.sistemaalumnosv2.menu_screen.ui.viewmodel.operation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.sistemaalumnosv2.menu_screen.data.ResourceMenu
import com.example.sistemaalumnosv2.menu_screen.data.model.DataStudent
import com.example.sistemaalumnosv2.menu_screen.domain.searchstudentcase.SearchStudentUseCase
import com.example.sistemaalumnosv2.menu_screen.ui.ResourceUIMenu
import com.example.sistemaalumnosv2.vo.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelOperation @Inject constructor(private val searchStudentUseCase: SearchStudentUseCase):ViewModel() {

    private val _studentDataModel = MutableLiveData<ResourceMenu<MutableList<DataStudent>>>()
    val studentDataModel : LiveData<ResourceMenu<MutableList<DataStudent>>>
        get() = _studentDataModel

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean>
        get() = _isLoading

    private val _exception = MutableLiveData<Exception>()
    val userException :LiveData<Exception>
        get() = _exception

    fun searchDataStudent(uid : String){
        viewModelScope.launch {
             _isLoading.postValue(true)
            when(val result = searchStudentUseCase.searchStudent(uid)){
                is ResourceMenu.Success -> {
                    _studentDataModel.postValue(result)
                }
                is ResourceMenu.Failure ->{
                    _exception.postValue(result.exception)
                }
            }
            _isLoading.postValue(false)
        }
    }
//    fun searchDataStudents(uid : String) = liveData(Dispatchers.IO) {
//        emit(Resource.Loading())
//
//        try {
//
//            val dataStudent = searchStudentUseCase.searchStudent(uid)
//            emit(dataStudent)
//
//        }catch (e:Exception){
//            emit(Resource.Failure(e))
//        }
//    }

}