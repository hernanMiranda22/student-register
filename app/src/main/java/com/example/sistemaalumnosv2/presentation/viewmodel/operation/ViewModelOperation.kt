package com.example.sistemaalumnosv2.presentation.viewmodel.operation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.sistemaalumnosv2.domain.searchstudentcase.SearchStudentUseCase
import com.example.sistemaalumnosv2.vo.Resource
import kotlinx.coroutines.Dispatchers

class ViewModelOperation(private val searchStudentUseCase: SearchStudentUseCase):ViewModel() {


    fun searchDataStudent(uid : String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())

        try {

            val dataStudent = searchStudentUseCase.searchStudent(uid)
            emit(dataStudent)

        }catch (e:Exception){
            emit(Resource.Failure(e))
        }
    }

}