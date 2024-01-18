package com.example.sistemaalumnosv2.domain.searchstudentcase

import com.example.sistemaalumnosv2.data.model.DataStudent
import com.example.sistemaalumnosv2.data.network.searchstudent.SearchStudentRepo
import com.example.sistemaalumnosv2.vo.Resource

class SearchStudentUseCaseImpl(private val searchStudentRepo: SearchStudentRepo):
    SearchStudentUseCase {
    override suspend fun searchStudent(uid : String): Resource<MutableList<DataStudent>> =
        searchStudentRepo.searchStudent(uid)
}