package com.example.sistemaalumnosv2.domain.signincase

import com.example.sistemaalumnosv2.data.model.User
import com.example.sistemaalumnosv2.data.network.sigin.SignInUserRepo
import com.example.sistemaalumnosv2.vo.Resource

class SignInUseCaseImpl(private val signInUserRepo: SignInUserRepo): SignInUseCase {
    override suspend fun signInWithEmail(email: String, password: String): Resource<User> =
        signInUserRepo.signInWithEmail(email, password)
}