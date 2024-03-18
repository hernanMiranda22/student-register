package com.example.sistemaalumnosv2.menu_screen.ui.viewmodel.grade

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sistemaalumnosv2.menu_screen.data.ResourceMenu
import com.example.sistemaalumnosv2.menu_screen.data.model.GradeStudent
import com.example.sistemaalumnosv2.menu_screen.data.model.TermData
import com.example.sistemaalumnosv2.menu_screen.domain.insertgradecase.GradeStudentUseCase
import com.example.sistemaalumnosv2.menu_screen.domain.searchgradecase.SearchGradeUseCase
import com.example.sistemaalumnosv2.menu_screen.ui.ResourceUIMenu
import com.example.sistemaalumnosv2.vo.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelGrade @Inject constructor(private val searchGradeUseCase: SearchGradeUseCase, private val gradeStudentUseCase: GradeStudentUseCase):ViewModel() {

    private val _gradeStudentModel = MutableLiveData<Resource<MutableList<GradeStudent>>>()
    val gradeStudentModel : LiveData<Resource<MutableList<GradeStudent>>>
        get() = _gradeStudentModel

    private val _termDataModel = MutableLiveData<ResourceUIMenu<TermData>>()
    val termDataModel : LiveData<ResourceUIMenu<TermData>>
        get() = _termDataModel

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean>
        get() = _isLoading

    private val _exception = MutableLiveData<Exception>()
    val userException :LiveData<Exception>
        get() = _exception

    fun getDataAndTerm(dni:Int, uid: String){
        viewModelScope.launch {
            when(val result = searchGradeUseCase.getGradeStudent(dni,uid)){
                is Resource.Loading ->{
                    _isLoading.postValue(true)
                }
                is Resource.Success -> {
                    _gradeStudentModel.postValue(result)
                }
                is Resource.Failure -> {
                    _exception.postValue(result.exception)
                }
            }
        }
    }

    fun insertGrade(dni:Int, firstTerm:Int,secondTerm:Int,thirdTerm:Int, uid : String){
        viewModelScope.launch {
            try {
                _termDataModel.postValue(ResourceUIMenu.Loading())

                val result = gradeStudentUseCase.insertGrade(dni, firstTerm, secondTerm, thirdTerm, uid)
                _termDataModel.postValue(ResourceUIMenu.Success(result) as ResourceUIMenu<TermData>)
            }catch (e: Exception){
                _termDataModel.postValue(ResourceUIMenu.Failure(e))
            }
//            _isLoading.postValue(true)
//            when(val result = gradeStudentUseCase.insertGrade(dni, firstTerm, secondTerm, thirdTerm, uid)){
//                is ResourceMenu.Success -> {
//                    _termDataModel.postValue(result)
//                }
//                is ResourceMenu.Failure -> {
//                    _exception.postValue(result.exception)
//                }
//            }
//            _isLoading.postValue(false)
        }
    }
}