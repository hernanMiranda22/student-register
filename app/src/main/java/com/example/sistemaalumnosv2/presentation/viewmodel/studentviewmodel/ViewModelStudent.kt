package com.example.sistemaalumnosv2.presentation.viewmodel.studentviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.sistemaalumnosv2.domain.insertstudentcase.InsertUseCase
import com.example.sistemaalumnosv2.vo.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class ViewModelStudent @Inject constructor(private val insertUseCase: InsertUseCase):ViewModel() {


    fun insertNewStudent(dni:Int, name:String, surname:String, year:String, idUser: String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading()) // Emit es lo mismo que usar .postValue(), este primer emit obtiene el primer valor para devolverlo con el LiveData

        try {

            val dataInsert = insertUseCase.insertStudent(dni, name, surname, year, idUser)
            emit(dataInsert)

        }catch (e : Exception){
            emit(Resource.Failure(e))
        }
    }

}