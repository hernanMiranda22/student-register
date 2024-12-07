package com.example.sistemaalumnosv2.vo

import com.google.firebase.auth.FirebaseAuth

object UserSingleton {

    fun getUserId() : String{
        val firebaseAuth = FirebaseAuth.getInstance()
        return firebaseAuth.currentUser?.uid.toString()
    }

}