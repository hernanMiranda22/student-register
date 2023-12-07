package com.example.sistemaalumnosv2.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.sistemaalumnosv2.domain.InsertUseCase
import com.example.sistemaalumnosv2.vo.Resource
import kotlinx.coroutines.Dispatchers

class ViewModelStudent(private val insertUseCase: InsertUseCase):ViewModel() {


    fun insertNewStudent(dni:Int, name:String, surname:String, year:String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading()) // Emit es lo mismo que usar .postValue(), este primer emit obtiene el primer valor para devolverlo con el LiveData

        try {

            val dataInsert = insertUseCase.insertStudent(dni, name, surname, year)
            emit(dataInsert)

        }catch (e : Exception){
            emit(Resource.Failure(e))
        }
    }

}