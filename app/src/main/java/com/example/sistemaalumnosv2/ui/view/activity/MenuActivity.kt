package com.example.sistemaalumnosv2.ui.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.viewpager2.widget.ViewPager2
import com.example.sistemaalumnosv2.R
import com.example.sistemaalumnosv2.databinding.ActivityMenuBinding
import com.example.sistemaalumnosv2.ui.view.adapter.ViewAdapter
import com.example.sistemaalumnosv2.ui.view.fragment.GradeFragment
import com.example.sistemaalumnosv2.ui.view.fragment.OperationFragment
import com.example.sistemaalumnosv2.ui.view.fragment.StudentDataFragment
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //loadNavigationBar()

        val viewAdapter = ViewAdapter(this)
        binding.vpContainer.adapter = viewAdapter

        binding.tbMenu.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
               binding.vpContainer.currentItem = tab!!.position
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Handle tab reselect
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Handle tab unselect
            }
        })


        binding.vpContainer.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tbMenu.getTabAt(position)?.select()
            }
        })
//        supportFragmentManager.commit {
//            setReorderingAllowed(true)
//            add<StudentDataFragment>(R.id.fcvMain)
//        }
    }

    //Lógica del BottomNavigation
//    private fun loadNavigationBar(){
//        binding.btmNavigation.setOnItemSelectedListener {
//            when(it.itemId){
//                //Navegacion hacia 'StudentDataFragment'(Vista principal)
//                R.id.btnInfoStudent ->{
//                    supportFragmentManager.commit{
//                        setReorderingAllowed(true)
//                        replace<StudentDataFragment>(R.id.fcvMain)
//                    }
//                    true
//                }
//                //Navegacion hacia 'OperationFragment(Vista secundaria)'
//                R.id.btnTermStudent ->{
//                    supportFragmentManager.commit{
//                        setReorderingAllowed(true)
//                        replace<GradeFragment>(R.id.fcvMain)
//                    }
//                    true
//                }
//                //Navegacion hacia 'OperationFragment'(Vista tercearia)
//                R.id.btnSearchStudent ->{
//                    supportFragmentManager.commit{
//                        setReorderingAllowed(true)
//                        replace<OperationFragment>(R.id.fcvMain)
//                    }
//                    true
//                }
//                else -> false
//            }
//        }
//    }
}