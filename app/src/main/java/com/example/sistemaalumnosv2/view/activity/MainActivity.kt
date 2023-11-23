package com.example.sistemaalumnosv2.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.sistemaalumnosv2.R
import com.example.sistemaalumnosv2.databinding.ActivityMainBinding
import com.example.sistemaalumnosv2.view.fragment.StudentDataFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadNavigationBar()

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<StudentDataFragment>(R.id.fcvMain)
        }
    }

    private fun loadNavigationBar(){
        binding.btmNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.btnInfoStudent ->{

                    true
                }
                R.id.btnSearchStudent ->{

                    true
                }
                R.id.btnGradeStudent ->{

                    true
                }
                else -> false
            }
        }
    }
}