package com.example.sistemaalumnosv2.presentation.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.sistemaalumnosv2.R
import com.example.sistemaalumnosv2.databinding.FragmentSignInBinding
import com.example.sistemaalumnosv2.presentation.view.activity.LoginActivity

class SingInFragment : Fragment() {

    private var _binding : FragmentSignInBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.tvSignIn.setOnClickListener {
            navigateToSignUpScreen()
        }
    }

    private fun navigateToSignUpScreen() {
        (activity as LoginActivity).supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<SignUpFragment>(R.id.fcvLogin)
        }
    }

}