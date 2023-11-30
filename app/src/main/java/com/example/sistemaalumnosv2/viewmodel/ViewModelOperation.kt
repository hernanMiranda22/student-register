package com.example.sistemaalumnosv2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sistemaalumnosv2.model.DataStudent
import com.example.sistemaalumnosv2.model.InsertGrade
import com.example.sistemaalumnosv2.model.InsertGradeRepository
import com.example.sistemaalumnosv2.model.SearchStudent
import com.example.sistemaalumnosv2.model.SearchStudentRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class ViewModelOperation:ViewModel() {

    private val searchStudent : SearchStudent = SearchStudentRepository()

    private val insertGrade : InsertGrade = InsertGradeRepository()

    fun searchStudent(dni:Int):LiveData<MutableList<DataStudent>>{
        val mutableLiveData = MutableLiveData<MutableList<DataStudent>>()

        viewModelScope.launch {
            searchStudent.searchStudent(dni).observeForever {
                mutableLiveData.value = it
            }
        }

        return mutableLiveData
    }

    fun insertGrade(grade: Int, dni:Int):LiveData<FirebaseFirestore>{
        val mutableGrade = MutableLiveData<FirebaseFirestore>()
        viewModelScope.launch {
            insertGrade.insertGradeStudent(grade, dni).observeForever {
                mutableGrade.value = it
            }
        }
        return mutableGrade
    }
}