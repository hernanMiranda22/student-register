package com.example.sistemaalumnosv2.menu_screen.domain.searchstudentcase

import com.example.sistemaalumnosv2.menu_screen.data.model.DataStudent
import com.example.sistemaalumnosv2.menu_screen.data.network.searchstudent.SearchStudentRepository
import com.example.sistemaalumnosv2.vo.Resource
import javax.inject.Inject

class SearchStudentUseCase @Inject constructor(private val searchStudentRepository: SearchStudentRepository) {
    suspend fun searchStudent(uid : String): Resource<MutableList<DataStudent>> =
        searchStudentRepository.searchStudent(uid)
}