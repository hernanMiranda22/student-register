package com.example.sistemaalumnosv2.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.sistemaalumnosv2.domain.GradeStudentUseCase
import com.example.sistemaalumnosv2.domain.SearchGradeUseCase
import com.example.sistemaalumnosv2.vo.Resource
import kotlinx.coroutines.Dispatchers

class ViewModelGrade(private val searchGradeUseCase: SearchGradeUseCase, private val gradeStudentUseCase: GradeStudentUseCase):ViewModel() {

    private val dispatchersIO = Dispatchers.IO
    fun getDataAndTerm(dni: Int) = liveData(dispatchersIO) {
        emit(Resource.Loading())

        try {

            val studentData = searchGradeUseCase.getGradeStudent(dni)
            emit(studentData)

        }catch (e:Exception){
            emit(Resource.Failure(e))
        }
    }

    fun insertGrade(dni:Int, firstTerm:Int,secondTerm:Int,thirdTerm:Int) = liveData(dispatchersIO) {

        emit(Resource.Loading())

        try {

            val gradeStudent = gradeStudentUseCase.insertGrade(dni, firstTerm, secondTerm, thirdTerm)
            emit(gradeStudent)

        }catch (e:Exception){
            emit(Resource.Failure(e))
        }
    }
}