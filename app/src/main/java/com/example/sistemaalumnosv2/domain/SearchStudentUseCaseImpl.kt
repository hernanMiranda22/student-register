package com.example.sistemaalumnosv2.domain

import com.example.sistemaalumnosv2.data.DataStudent
import com.example.sistemaalumnosv2.data.network.SearchStudentRepo
import com.example.sistemaalumnosv2.vo.Resource

class SearchStudentUseCaseImpl(private val searchStudentRepo: SearchStudentRepo):SearchStudentUseCase {
    override suspend fun searchStudent(dni: Int): Resource<MutableList<DataStudent>> =
        searchStudentRepo.searchStudent(dni)
}