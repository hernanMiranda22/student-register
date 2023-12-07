package com.example.sistemaalumnosv2.presentation.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.sistemaalumnosv2.R
import com.example.sistemaalumnosv2.databinding.ActivityMainBinding
import com.example.sistemaalumnosv2.presentation.view.fragment.OperationFragment
import com.example.sistemaalumnosv2.presentation.view.fragment.StudentDataFragment

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

    //Lógica del BottomNavigation
    private fun loadNavigationBar(){
        binding.btmNavigation.setOnItemSelectedListener {
            when(it.itemId){
                //Navegacion hacia 'StudentDataFragment'(Vista principal)
                R.id.btnInfoStudent ->{
                    supportFragmentManager.commit{
                        setReorderingAllowed(true)
                        replace<StudentDataFragment>(R.id.fcvMain)
                    }
                    true
                }
                //Navegacion hacia 'OperationFragment'(Vista secundaria)
                R.id.btnSearchStudent ->{
                    supportFragmentManager.commit{
                        setReorderingAllowed(true)
                        replace<OperationFragment>(R.id.fcvMain)
                    }
                    true
                }
                else -> false
            }
        }
    }
}