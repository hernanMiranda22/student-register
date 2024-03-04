package com.example.sistemaalumnosv2.login_screen.ui.view.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.sistemaalumnosv2.login_screen.ui.view.fragment.SignUpFragment
import com.example.sistemaalumnosv2.login_screen.ui.view.fragment.SingInFragment

class LoginAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> SingInFragment()
            1 -> SignUpFragment()
            else -> SingInFragment()
        }
    }
}