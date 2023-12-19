package com.example.sistemaalumnosv2.domain

import com.example.sistemaalumnosv2.data.model.GradeStudent
import com.example.sistemaalumnosv2.vo.Resource

interface SearchGradeUseCase {
    suspend fun getGradeStudent(dni:Int): Resource<MutableList<GradeStudent>>
}