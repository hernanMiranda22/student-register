package com.example.sistemaalumnosv2.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.sistemaalumnosv2.data.DataStudent
import com.example.sistemaalumnosv2.data.network.GradeStudentRepo
import com.example.sistemaalumnosv2.data.network.SearchStudentRepo
import com.example.sistemaalumnosv2.domain.GradeStudentUseCase
import com.example.sistemaalumnosv2.domain.SearchStudentUseCase
import com.example.sistemaalumnosv2.model.InsertGrade
import com.example.sistemaalumnosv2.model.InsertGradeRepository
import com.example.sistemaalumnosv2.model.SearchStudent
import com.example.sistemaalumnosv2.model.SearchStudentRepository
import com.example.sistemaalumnosv2.vo.Resource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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


   //Funciones sin google-play-services


//    private val searchStudent : SearchStudent = SearchStudentRepository()
//
//    private val insertGrade : InsertGrade = InsertGradeRepository()
//
//    fun searchStudent(dni:Int):LiveData<MutableList<DataStudent>>{
//        val mutableLiveData = MutableLiveData<MutableList<DataStudent>>()
//
//        viewModelScope.launch {
//            searchStudent.searchStudent(dni).observeForever {
//                mutableLiveData.value = it
//            }
//        }
//
//        return mutableLiveData
//    }
//
//    fun insertGrade(grade: Int, dni:Int):LiveData<FirebaseFirestore>{
//        val mutableGrade = MutableLiveData<FirebaseFirestore>()
//        viewModelScope.launch {
//            insertGrade.insertGradeStudent(grade, dni).observeForever {
//                mutableGrade.value = it
//            }
//        }
//        return mutableGrade
//    }
}