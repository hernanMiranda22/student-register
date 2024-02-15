package com.example.sistemaalumnosv2.domain.insertstudentcase

import com.example.sistemaalumnosv2.data.network.insertstudent.InsertStudentRepo
import com.example.sistemaalumnosv2.vo.Resource
import javax.inject.Inject

class InsertUseCaseImpl @Inject constructor(private val insertStudentRepo: InsertStudentRepo): InsertUseCase {
    override suspend fun insertStudent(
        dni: Int,
        name: String,
        surname: String,
        year: String,
        idUser: String
    ): Resource<Int> =
        insertStudentRepo.insertStudent(dni, name, surname, year,idUser)

}