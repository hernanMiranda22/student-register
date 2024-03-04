package com.example.sistemaalumnosv2.menu_screen.ui.view.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.sistemaalumnosv2.menu_screen.ui.view.fragment.GradeFragment
import com.example.sistemaalumnosv2.menu_screen.ui.view.fragment.OperationFragment
import com.example.sistemaalumnosv2.menu_screen.ui.view.fragment.StudentDataFragment

class ViewAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
       return when(position){
           0-> StudentDataFragment()
           1-> GradeFragment()
           2-> OperationFragment()
           else -> StudentDataFragment()
       }
    }
}