package com.example.sistemaalumnosv2.login_screen.domain.signincase

import com.example.sistemaalumnosv2.login_screen.data.model.User
import com.example.sistemaalumnosv2.login_screen.data.network.sigin.SignInUserRepository
import com.example.sistemaalumnosv2.vo.Resource
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val signInUserRepository: SignInUserRepository) {
     suspend fun signInWithEmail(email: String, password: String): Resource<User> =
        signInUserRepository .signInWithEmail(email, password)
}