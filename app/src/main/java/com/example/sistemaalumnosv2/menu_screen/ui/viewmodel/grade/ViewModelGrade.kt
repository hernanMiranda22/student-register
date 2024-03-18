package com.example.sistemaalumnosv2.menu_screen.ui.viewmodel.grade

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.sistemaalumnosv2.menu_screen.data.model.GradeStudent
import com.example.sistemaalumnosv2.menu_screen.data.model.TermData
import com.example.sistemaalumnosv2.menu_screen.domain.insertgradecase.GradeStudentUseCase
import com.example.sistemaalumnosv2.menu_screen.domain.searchgradecase.SearchGradeUseCase
import com.example.sistemaalumnosv2.vo.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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
            when(val result = gradeStudentUseCase.insertGrade(dni, firstTerm, secondTerm, thirdTerm, uid)){
                is Resource.Loading ->{
                    _isLoading.postValue(true)
                }
                is Resource.Success -> {
                    _termDataModel.postValue(result)
                }
                is Resource.Failure -> {
                    _exception.postValue(result.exception)
                }
            }
        }
    }


//    private val dispatchersIO = Dispatchers.IO
//    fun getDataAndTerm(dni:Int, uid: String) = liveData(dispatchersIO) {
//        emit(Resource.Loading())
//
//        try {
//
//            val studentData = searchGradeUseCase.getGradeStudent(dni,uid)
//            emit(studentData)
//
//        }catch (e:Exception){
//            emit(Resource.Failure(e))
//        }
//    }
//
//    fun insertGrade(dni:Int, firstTerm:Int,secondTerm:Int,thirdTerm:Int, uid : String) = liveData(dispatchersIO) {
//
//        emit(Resource.Loading())
//
//        try {
//
//            val gradeStudent = gradeStudentUseCase.insertGrade(dni, firstTerm, secondTerm, thirdTerm, uid)
//            emit(gradeStudent)
//
//        }catch (e:Exception){
//            emit(Resource.Failure(e))
//        }
//    }
}