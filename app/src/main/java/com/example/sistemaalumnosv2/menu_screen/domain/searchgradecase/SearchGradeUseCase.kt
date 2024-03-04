package com.example.sistemaalumnosv2.menu_screen.domain.searchgradecase

import com.example.sistemaalumnosv2.menu_screen.data.model.GradeStudent
import com.example.sistemaalumnosv2.menu_screen.data.network.searchgrade.SearchGradeRepository
import com.example.sistemaalumnosv2.vo.Resource
import javax.inject.Inject

class SearchGradeUseCase @Inject constructor(private val searchGradeRepository: SearchGradeRepository) {
    suspend fun getGradeStudent(dni:Int, uid: String): Resource<MutableList<GradeStudent>> =
        searchGradeRepository.getGradeStudent(dni, uid)
}