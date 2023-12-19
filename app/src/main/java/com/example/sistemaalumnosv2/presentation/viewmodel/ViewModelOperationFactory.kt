package com.example.sistemaalumnosv2.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sistemaalumnosv2.domain.GradeStudentUseCase
import com.example.sistemaalumnosv2.domain.SearchStudentUseCase

class ViewModelOperationFactory(private val searchStudentUseCase: SearchStudentUseCase):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(SearchStudentUseCase::class.java).newInstance(searchStudentUseCase)
    }
}