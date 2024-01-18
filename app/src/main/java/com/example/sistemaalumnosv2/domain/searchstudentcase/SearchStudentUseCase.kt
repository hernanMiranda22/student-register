package com.example.sistemaalumnosv2.domain.searchstudentcase

import com.example.sistemaalumnosv2.data.model.DataStudent
import com.example.sistemaalumnosv2.data.model.GradeStudent
import com.example.sistemaalumnosv2.vo.Resource

interface SearchStudentUseCase {
    suspend fun searchStudent(uid: String):Resource<MutableList<DataStudent>>
}