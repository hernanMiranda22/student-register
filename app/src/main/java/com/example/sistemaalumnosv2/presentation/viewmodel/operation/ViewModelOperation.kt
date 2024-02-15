package com.example.sistemaalumnosv2.presentation.viewmodel.operation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.sistemaalumnosv2.domain.searchstudentcase.SearchStudentUseCase
import com.example.sistemaalumnosv2.vo.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class ViewModelOperation @Inject constructor(private val searchStudentUseCase: SearchStudentUseCase):ViewModel() {


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