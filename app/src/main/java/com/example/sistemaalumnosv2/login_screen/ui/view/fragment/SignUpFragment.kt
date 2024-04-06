package com.example.sistemaalumnosv2.login_screen.ui.view.fragment

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.sistemaalumnosv2.R
import com.example.sistemaalumnosv2.databinding.FragmentSignUpBinding
import com.example.sistemaalumnosv2.login_screen.ui.view.activity.LoginActivity
import com.example.sistemaalumnosv2.login_screen.ui.viewmodel.ViewModelSignUp
import com.example.sistemaalumnosv2.vo.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private var _binding : FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    private val viewModelSignUp : ViewModelSignUp by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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

        if (email.isEmpty() || !isValidEmail(email)){
            binding.etEmailSignUp.error = getString(R.string.helperErrorEmail)
        }else if (password.isEmpty() || password.length < 6){
            Toast.makeText(activity as LoginActivity, "Error en la contraseña", Toast.LENGTH_SHORT).show()
        }else if (passwordConfirm.isEmpty() || passwordConfirm != password){
            Toast.makeText(activity as LoginActivity, "Error al confirmar la contraseña", Toast.LENGTH_SHORT).show()
        }else{
            viewModelSignUp.signUpNewUser(email, password)

            viewModelSignUp.isLoading.observe(viewLifecycleOwner){
                binding.cpSignUp.isVisible = it
            }

            viewModelSignUp.userModel.observe(viewLifecycleOwner){result ->
                when(result){
                    is Resource.Success -> {
                        Toast.makeText(activity as LoginActivity, "Usuario creado exitosamente", Toast.LENGTH_SHORT).show()
                        clearText()
                    }
                    is Resource.Failure -> {
                        authValidate(result.exception.message)
                        Toast.makeText(activity as LoginActivity, "Error al crear la cuenta", Toast.LENGTH_SHORT).show()
                    }
                }

                viewModelSignUp.userException.observe(viewLifecycleOwner){
                    Log.e("Error SignUp","$it")
                }
            }
        }
    }

    private fun isValidEmail(email: String):Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun authValidate(error : String?){
        when(error){
            "The email address is already in use by another account." -> {
                Toast.makeText(activity as LoginActivity, getString(R.string.helperErrorEmailUsing), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun clearText(){
        binding.etEmailSignUp.setText("")
        binding.etPasswordSignUp.setText("")
        binding.etConfirmSingUp.setText("")
    }


}