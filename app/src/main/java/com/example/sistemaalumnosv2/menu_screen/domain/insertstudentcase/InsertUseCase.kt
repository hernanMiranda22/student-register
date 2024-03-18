package com.example.sistemaalumnosv2.menu_screen.domain.insertstudentcase

import com.example.sistemaalumnosv2.menu_screen.data.ResourceMenu
import com.example.sistemaalumnosv2.menu_screen.data.model.DataStudent
import com.example.sistemaalumnosv2.menu_screen.data.network.insertstudent.InsertStudentRepository
import com.example.sistemaalumnosv2.vo.Resource
import javax.inject.Inject

class InsertUseCase @Inject constructor(private val insertStudentRepository: InsertStudentRepository) {
    suspend fun insertStudent(
        dni: Int,
        name: String,
        surname: String,
        year: String,
        idUser: String
    ): ResourceMenu<DataStudent> =
        insertStudentRepository.insertStudent(dni, name, surname, year,idUser)
}