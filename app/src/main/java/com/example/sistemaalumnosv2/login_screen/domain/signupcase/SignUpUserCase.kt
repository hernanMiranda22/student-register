package com.example.sistemaalumnosv2.login_screen.domain.signupcase

import com.example.sistemaalumnosv2.login_screen.data.model.User
import com.example.sistemaalumnosv2.login_screen.data.network.signupemail.SignUpUserRepository
import com.example.sistemaalumnosv2.vo.Resource
import javax.inject.Inject

class SignUpUserCase @Inject constructor(private val signUpUserRepository: SignUpUserRepository){
    suspend fun signUpEmailAndPassword(email: String, password: String): Resource<User> =
        signUpUserRepository.signUpEmailAndPassword(email, password)
}