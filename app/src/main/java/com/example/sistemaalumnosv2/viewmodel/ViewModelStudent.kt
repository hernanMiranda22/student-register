package com.example.sistemaalumnosv2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sistemaalumnosv2.model.CreateStudent
import com.example.sistemaalumnosv2.model.CreateStudentRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class ViewModelStudent:ViewModel() {

    private val createStudent: CreateStudent = CreateStudentRepository()

    fun createStudent(dni: Int, name: String, surname: String, year: String):LiveData<FirebaseFirestore>{
        val mutableStudent = MutableLiveData<FirebaseFirestore>()

        viewModelScope.launch {
            createStudent.createStudent(dni, name, surname, year).observeForever {
                mutableStudent.value = it
            }
        }

        return mutableStudent
    }
}