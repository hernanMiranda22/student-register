package com.example.sistemaalumnosv2.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.sistemaalumnosv2.domain.GradeStudentUseCase
import com.example.sistemaalumnosv2.domain.SearchStudentUseCase
import com.example.sistemaalumnosv2.vo.Resource
import kotlinx.coroutines.Dispatchers

class ViewModelOperation(private val searchStudentUseCase: SearchStudentUseCase, private val gradeStudentUseCase: GradeStudentUseCase):ViewModel() {


    fun searchDataStudent(dni:Int) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())

        try {

            val dataStudent = searchStudentUseCase.searchStudent(dni)
            emit(dataStudent)

        }catch (e:Exception){
            emit(Resource.Failure(e))
        }
    }

    fun insertGrade(dni:Int, grade: Int) = liveData(Dispatchers.IO) {

        emit(Resource.Loading())

        try {

            val gradeStudent = gradeStudentUseCase.insertGrade(dni, grade)
            emit(gradeStudent)

        }catch (e:Exception){
            emit(Resource.Failure(e))
        }
    }

}