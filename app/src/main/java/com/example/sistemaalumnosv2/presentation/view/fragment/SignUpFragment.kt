package com.example.sistemaalumnosv2.presentation.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.sistemaalumnosv2.R
import com.example.sistemaalumnosv2.data.network.SignUpUserRepoImpl
import com.example.sistemaalumnosv2.databinding.FragmentSignUpBinding
import com.example.sistemaalumnosv2.domain.SignUpUserCaseImpl
import com.example.sistemaalumnosv2.presentation.view.activity.LoginActivity
import com.example.sistemaalumnosv2.presentation.viewmodel.ViewModelSignUp
import com.example.sistemaalumnosv2.presentation.viewmodel.ViewModelSignUpFactory
import com.example.sistemaalumnosv2.vo.Resource

class SignUpFragment : Fragment() {

    private var _binding : FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    private val viewModelSignUp by lazy { ViewModelProvider(this, ViewModelSignUpFactory(SignUpUserCaseImpl(SignUpUserRepoImpl())))[ViewModelSignUp::class.java] }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnSignUp.setOnClickListener {
            observerSignUp()
        }
    }

    private fun observerSignUp() {
        val email = binding.etEmailSignUp.text.toString()
        val password = binding.etPasswordSignUp.text.toString()
        val passwordConfirm = binding.etConfirmSingUp.text.toString()

        if (email.isEmpty()){
            Toast.makeText(activity as LoginActivity, "Error en el mail", Toast.LENGTH_SHORT).show()
        }else if (password.isEmpty() || password.length < 4){
            Toast.makeText(activity as LoginActivity, "Error en la contraseña", Toast.LENGTH_SHORT).show()
        }else if (passwordConfirm.isEmpty() || passwordConfirm != password){
            Toast.makeText(activity as LoginActivity, "Error al confirmar la contraseña", Toast.LENGTH_SHORT).show()
        }else{
            viewModelSignUp.signUpNewUser(email, password).observe(viewLifecycleOwner){result ->
                when(result){
                    is Resource.Loading -> {

                    }
                    is Resource.Success -> {
                        Toast.makeText(activity as LoginActivity, "Usuario creado exitosamente", Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Failure -> {
                        Log.e("Error SignUp","${result.exception}")
                    }
                }
            }
        }

    }
}