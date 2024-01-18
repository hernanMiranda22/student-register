package com.example.sistemaalumnosv2.domain.insertgradecase

import com.example.sistemaalumnosv2.data.network.insertgrade.GradeStudentRepo
import com.example.sistemaalumnosv2.vo.Resource

class GradeStudentUseCaseImpl(private val gradeStudentRepo: GradeStudentRepo): GradeStudentUseCase {
    override suspend fun insertGrade(dni:Int, firstTerm:Int,secondTerm:Int,thirdTerm:Int, uid : String): Resource<Int> =
        gradeStudentRepo.insertGrade(dni, firstTerm, secondTerm, thirdTerm, uid)
}