package com.example.sistemaalumnosv2.presentation.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.sistemaalumnosv2.R
import com.example.sistemaalumnosv2.databinding.ActivityLoginBinding
import com.example.sistemaalumnosv2.presentation.view.fragment.SingInFragment

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<SingInFragment>(R.id.fcvLogin)
        }

    }
}