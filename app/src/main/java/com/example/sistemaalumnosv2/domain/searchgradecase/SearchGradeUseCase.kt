package com.example.sistemaalumnosv2.domain.searchgradecase

import com.example.sistemaalumnosv2.data.model.GradeStudent
import com.example.sistemaalumnosv2.vo.Resource

interface SearchGradeUseCase {
    suspend fun getGradeStudent(dni:Int, uid: String): Resource<MutableList<GradeStudent>>
}