package com.example.sistemaalumnosv2.login_screen.ui.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.sistemaalumnosv2.databinding.ActivityLoginBinding
import com.example.sistemaalumnosv2.login_screen.ui.view.adapter.LoginAdapter
import com.example.sistemaalumnosv2.menu_screen.ui.view.adapter.ViewAdapter
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val loginAdapter = LoginAdapter(this)
        binding.vpContainerLogin.adapter = loginAdapter

        binding.tlLogin.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.vpContainerLogin.currentItem = tab!!.position
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Handle tab reselect
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Handle tab unselect
            }
        })

        binding.vpContainerLogin.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tlLogin.getTabAt(position)?.select()
            }
        })

//        supportFragmentManager.commit {
//            setReorderingAllowed(true)
//            add<SingInFragment>(R.id.fcvLogin)
//        }

    }
}