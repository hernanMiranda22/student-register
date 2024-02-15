package com.example.sistemaalumnosv2.di

import com.example.sistemaalumnosv2.data.network.insertgrade.GradeStudentRepo
import com.example.sistemaalumnosv2.data.network.insertgrade.GradeStudentRepoImpl
import com.example.sistemaalumnosv2.data.network.searchgrade.SearchGradeRepo
import com.example.sistemaalumnosv2.data.network.searchgrade.SearchGradeRepoImpl
import com.example.sistemaalumnosv2.domain.insertgradecase.GradeStudentUseCase
import com.example.sistemaalumnosv2.domain.insertgradecase.GradeStudentUseCaseImpl
import com.example.sistemaalumnosv2.domain.searchgradecase.SearchGradeUseCase
import com.example.sistemaalumnosv2.domain.searchgradecase.SearchGradeUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {

    @Binds
    abstract fun bindInsetGradeUseCaseImp(gradeStudentUseCaseImpl: GradeStudentUseCaseImpl) : GradeStudentUseCase

    @Binds
    abstract fun bindSearchGradeUseCaseImpl(searchGradeUseCaseImpl: SearchGradeUseCaseImpl) : SearchGradeUseCase

    @Binds
    abstract fun bindInsertGradeRepoImpl(gradeStudentRepoImpl: GradeStudentRepoImpl) : GradeStudentRepo

    @Binds
    abstract fun bindSearchGradeRepoImpl(searchGradeRepoImpl: SearchGradeRepoImpl) : SearchGradeRepo

}