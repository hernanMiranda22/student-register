package com.example.sistemaalumnosv2.menu_screen.ui.viewmodel.operation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sistemaalumnosv2.menu_screen.data.model.DataStudent
import com.example.sistemaalumnosv2.menu_screen.domain.searchstudentcase.SearchStudentUseCase
import com.example.sistemaalumnosv2.menu_screen.ui.model.ResourceMenu
import com.example.sistemaalumnosv2.vo.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelOperation @Inject constructor(private val searchStudentUseCase: SearchStudentUseCase):ViewModel() {

    private val _studentDataModel = MutableLiveData<Resource<MutableList<DataStudent>>>()
    val studentDataModel : LiveData<Resource<MutableList<DataStudent>>>
        get() = _studentDataModel


    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean>
        get() = _isLoading

    private val _exception = MutableLiveData<Exception>()
    val exception :LiveData<Exception>
        get() = _exception


    private val _listStudent = MutableStateFlow<ResourceMenu>(
        ResourceMenu.Loading
    )

    val listStudent : StateFlow<ResourceMenu> = _listStudent.asStateFlow()

    fun getDataStudent(uid : String){
        viewModelScope.launch {
            searchStudentUseCase.getAllStudents(uid).collect{ list ->
                _listStudent.update { ResourceMenu.Success(list) }
            }

        }
    }


//    fun searchDataStudent(uid : String){
//        viewModelScope.launch {
//             _isLoading.postValue(true)
//            when(val result = searchStudentUseCase.searchStudent(uid)){
//                is Resource.Success -> {
//                    _studentDataModel.postValue(result)
//                }
//                is Resource.Failure ->{
//                    _exception.postValue(result.exception)
//                }
//            }
//            _isLoading.postValue(false)
//        }
//    }
}