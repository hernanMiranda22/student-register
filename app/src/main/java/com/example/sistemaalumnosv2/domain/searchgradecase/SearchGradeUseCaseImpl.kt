package com.example.sistemaalumnosv2.domain.searchgradecase

import com.example.sistemaalumnosv2.data.model.GradeStudent
import com.example.sistemaalumnosv2.data.network.searchgrade.SearchGradeRepo
import com.example.sistemaalumnosv2.vo.Resource

class SearchGradeUseCaseImpl(private val searchGradeRepo: SearchGradeRepo): SearchGradeUseCase {
    override suspend fun getGradeStudent(dni:Int, uid: String): Resource<MutableList<GradeStudent>> =
        searchGradeRepo.getGradeStudent(dni, uid)
}