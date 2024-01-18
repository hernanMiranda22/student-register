package com.example.sistemaalumnosv2.presentation.viewmodel.studentviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sistemaalumnosv2.domain.insertstudentcase.InsertUseCase

class ViewModelStudentFactory(private val insertUseCase: InsertUseCase): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(InsertUseCase::class.java).newInstance(insertUseCase)
    }
}