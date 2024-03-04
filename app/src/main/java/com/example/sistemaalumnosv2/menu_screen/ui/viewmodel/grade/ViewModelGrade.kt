package com.example.sistemaalumnosv2.menu_screen.ui.viewmodel.grade

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.sistemaalumnosv2.menu_screen.domain.insertgradecase.GradeStudentUseCase
import com.example.sistemaalumnosv2.menu_screen.domain.searchgradecase.SearchGradeUseCase
import com.example.sistemaalumnosv2.vo.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class ViewModelGrade @Inject constructor(private val searchGradeUseCase: SearchGradeUseCase, private val gradeStudentUseCase: GradeStudentUseCase):ViewModel() {

    private val dispatchersIO = Dispatchers.IO
    fun getDataAndTerm(dni:Int, uid: String) = liveData(dispatchersIO) {
        emit(Resource.Loading())

        try {

            val studentData = searchGradeUseCase.getGradeStudent(dni,uid)
            emit(studentData)

        }catch (e:Exception){
            emit(Resource.Failure(e))
        }
    }

    fun insertGrade(dni:Int, firstTerm:Int,secondTerm:Int,thirdTerm:Int, uid : String) = liveData(dispatchersIO) {

        emit(Resource.Loading())

        try {

            val gradeStudent = gradeStudentUseCase.insertGrade(dni, firstTerm, secondTerm, thirdTerm, uid)
            emit(gradeStudent)

        }catch (e:Exception){
            emit(Resource.Failure(e))
        }
    }
}