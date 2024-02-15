package com.example.sistemaalumnosv2.presentation.viewmodel.grade

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sistemaalumnosv2.domain.insertgradecase.GradeStudentUseCase
import com.example.sistemaalumnosv2.domain.searchgradecase.SearchGradeUseCase

//class ViewModelGradeFactory(private val searchGradeUseCase: SearchGradeUseCase, private val gradeStudentUseCase: GradeStudentUseCase):ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return modelClass.getConstructor(SearchGradeUseCase::class.java, GradeStudentUseCase::class.java).newInstance(searchGradeUseCase, gradeStudentUseCase)
//    }
//}