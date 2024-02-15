package com.example.sistemaalumnosv2.di

import com.example.sistemaalumnosv2.data.network.insertgrade.GradeStudentRepo
import com.example.sistemaalumnosv2.data.network.insertgrade.GradeStudentRepoImpl
import com.example.sistemaalumnosv2.data.network.insertstudent.InsertStudentRepo
import com.example.sistemaalumnosv2.data.network.insertstudent.InsertStudentRepoImpl
import com.example.sistemaalumnosv2.data.network.searchgrade.SearchGradeRepo
import com.example.sistemaalumnosv2.data.network.searchgrade.SearchGradeRepoImpl
import com.example.sistemaalumnosv2.data.network.searchstudent.SearchStudentRepo
import com.example.sistemaalumnosv2.data.network.searchstudent.SearchStudentRepoImpl
import com.example.sistemaalumnosv2.data.network.sigin.SignInUserRepo
import com.example.sistemaalumnosv2.data.network.sigin.SignInUserRepoImpl
import com.example.sistemaalumnosv2.data.network.signupemail.SignUpUserRepo
import com.example.sistemaalumnosv2.data.network.signupemail.SignUpUserRepoImpl
import com.example.sistemaalumnosv2.domain.insertgradecase.GradeStudentUseCase
import com.example.sistemaalumnosv2.domain.insertgradecase.GradeStudentUseCaseImpl
import com.example.sistemaalumnosv2.domain.insertstudentcase.InsertUseCase
import com.example.sistemaalumnosv2.domain.insertstudentcase.InsertUseCaseImpl
import com.example.sistemaalumnosv2.domain.searchgradecase.SearchGradeUseCase
import com.example.sistemaalumnosv2.domain.searchgradecase.SearchGradeUseCaseImpl
import com.example.sistemaalumnosv2.domain.searchstudentcase.SearchStudentUseCase
import com.example.sistemaalumnosv2.domain.searchstudentcase.SearchStudentUseCaseImpl
import com.example.sistemaalumnosv2.domain.signincase.SignInUseCase
import com.example.sistemaalumnosv2.domain.signincase.SignInUseCaseImpl
import com.example.sistemaalumnosv2.domain.signupcase.SignUpUserCaseImpl
import com.example.sistemaalumnosv2.domain.signupcase.SignUpUserUseCase
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
    abstract fun bindInsertGradeRepoImpl(gradeStudentRepoImpl: GradeStudentRepoImpl) : GradeStudentRepo


    @Binds
    abstract fun bindSearchGradeUseCaseImpl(searchGradeUseCaseImpl: SearchGradeUseCaseImpl) : SearchGradeUseCase
    @Binds
    abstract fun bindSearchGradeRepoImpl(searchGradeRepoImpl: SearchGradeRepoImpl) : SearchGradeRepo


    @Binds
    abstract fun bindInsertUseCaseImpl(insertUseCaseImpl: InsertUseCaseImpl) : InsertUseCase
    @Binds
    abstract fun bindInsertStudentRepoImpl(insertStudentRepoImpl: InsertStudentRepoImpl) : InsertStudentRepo


    @Binds
    abstract fun bindSearchStudentUseCaseImpl(searchStudentUseCaseImpl: SearchStudentUseCaseImpl) : SearchStudentUseCase
    @Binds
    abstract fun bindSearchStudentRepoImpl(searchStudentRepoImpl: SearchStudentRepoImpl) : SearchStudentRepo


    @Binds
    abstract fun bindSingInUseCaseImpl(singInUseCaseImpl: SignInUseCaseImpl) : SignInUseCase
    @Binds
    abstract fun bindSingInRepoImpl(singInUserRepoImpl: SignInUserRepoImpl) : SignInUserRepo


    @Binds
    abstract fun bindSingUpUseCaseImpl(singUpUseCaseImpl: SignUpUserCaseImpl) : SignUpUserUseCase
    @Binds
    abstract fun bindSignUpUserRepoImpl(singUpUserRepoImpl: SignUpUserRepoImpl) : SignUpUserRepo
}