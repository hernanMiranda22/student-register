package com.example.sistemaalumnosv2.menu_screen.domain.insertgradecase

import com.example.sistemaalumnosv2.menu_screen.data.model.GradeStudent
import com.example.sistemaalumnosv2.menu_screen.data.model.TermData
import com.example.sistemaalumnosv2.menu_screen.data.network.insertgrade.GradeStudentRepository
import com.example.sistemaalumnosv2.vo.Resource
import javax.inject.Inject

class GradeStudentUseCase @Inject constructor(private val gradeStudentRepository: GradeStudentRepository){
     suspend fun insertGrade(dni:Int, firstTerm:Int,secondTerm:Int,thirdTerm:Int, uid : String): Resource<TermData> =
        gradeStudentRepository.insertGrade(dni, firstTerm, secondTerm, thirdTerm, uid)
}