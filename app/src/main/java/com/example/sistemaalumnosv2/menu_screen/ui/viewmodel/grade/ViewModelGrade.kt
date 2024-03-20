package com.example.sistemaalumnosv2.menu_screen.ui.viewmodel.grade

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sistemaalumnosv2.menu_screen.data.model.GradeStudent
import com.example.sistemaalumnosv2.menu_screen.data.model.TermData
import com.example.sistemaalumnosv2.menu_screen.domain.insertgradecase.GradeStudentUseCase
import com.example.sistemaalumnosv2.menu_screen.domain.searchgradecase.SearchGradeUseCase
import com.example.sistemaalumnosv2.vo.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelGrade @Inject constructor(private val searchGradeUseCase: SearchGradeUseCase, private val gradeStudentUseCase: GradeStudentUseCase):ViewModel() {

    private val _gradeStudentModel = MutableLiveData<Resource<MutableList<GradeStudent>>>()
    val gradeStudentModel : LiveData<Resource<MutableList<GradeStudent>>>
        get() = _gradeStudentModel

    private val _termDataModel = MutableLiveData<Resource<TermData>>()
    val termDataModel : LiveData<Resource<TermData>>
        get() = _termDataModel

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean>
        get() = _isLoading

    private val _exception = MutableLiveData<Exception>()
    val exception :LiveData<Exception>
        get() = _exception

    fun getDataAndTerm(dni:Int, uid: String){
        viewModelScope.launch {
            _isLoading.postValue(true)

            when(val result = searchGradeUseCase.getGradeStudent(dni,uid)){
                is Resource.Success -> {
                    _gradeStudentModel.postValue(result)
                }
                is Resource.Failure -> {
                    _exception.postValue(result.exception)
                }
            }

            _isLoading.postValue(false)
        }
    }

    fun insertGrade(dni:Int, firstTerm:Int,secondTerm:Int,thirdTerm:Int, uid : String){
        viewModelScope.launch {
            _isLoading.postValue(true)

            when(val result = gradeStudentUseCase.insertGrade(dni, firstTerm, secondTerm, thirdTerm, uid)){
                is Resource.Success -> {
                    _termDataModel.postValue(result)
                }
                is Resource.Failure -> {
                    _exception.postValue(result.exception)
                }
            }

            _isLoading.postValue(false)
        }
    }
}