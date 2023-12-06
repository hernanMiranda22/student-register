package com.example.sistemaalumnosv2.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.sistemaalumnosv2.data.Student
import com.example.sistemaalumnosv2.domain.InsertUseCase
import com.example.sistemaalumnosv2.model.CreateStudent
import com.example.sistemaalumnosv2.model.CreateStudentRepository
import com.example.sistemaalumnosv2.vo.Resource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelStudent(private val insertUseCase: InsertUseCase):ViewModel() {

    val student = Student()

    fun insertNewStudent(dni:Int, name:String, surname:String, year:String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading()) // Emit es lo mismo que usar .postValue(), este primer emit obtiene el primer valor para devolverlo con el LiveData

        try {

            val version = insertUseCase.insertStudent(dni, name, surname, year)
            emit(version)

        }catch (e : Exception){
            emit(Resource.Failure(e))
        }
    }



//    val insertNewStudent = liveData(Dispatchers.IO) {   //Este livedata retorna un valor "Any" o sea que se queda con el ultimo valor que tome el ultimo emit
//        emit(Resource.Loading()) // Emit es lo mismo que usar .postValue(), este primer emit obtiene el primer valor para devolverlo con el LiveData
//
//        try {
//
//            val version = insertUseCase.insertStudent(dni = student.dni, name = student.name, surname =  student.surname, year = student.year)
//            emit(version)
//
//        }catch (e : Exception){
//            emit(Resource.Failure(e))
//        }
//    }



    // Corrutina que no usa google-play-services
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