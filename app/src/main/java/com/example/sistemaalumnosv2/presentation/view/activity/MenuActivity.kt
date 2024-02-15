package com.example.sistemaalumnosv2.presentation.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.sistemaalumnosv2.R
import com.example.sistemaalumnosv2.databinding.ActivityMenuBinding
import com.example.sistemaalumnosv2.presentation.view.fragment.GradeFragment
import com.example.sistemaalumnosv2.presentation.view.fragment.OperationFragment
import com.example.sistemaalumnosv2.presentation.view.fragment.StudentDataFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
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
                //Navegacion hacia 'OperationFragment(Vista secundaria)'
                R.id.btnTermStudent ->{
                    supportFragmentManager.commit{
                        setReorderingAllowed(true)
                        replace<GradeFragment>(R.id.fcvMain)
                    }
                    true
                }
                //Navegacion hacia 'OperationFragment'(Vista tercearia)
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