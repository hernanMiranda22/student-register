package com.example.sistemaalumnosv2.login_screen.ui.view.fragment

import android.app.Activity.RESULT_OK
import android.content.Intent
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
import androidx.navigation.fragment.findNavController
import com.example.sistemaalumnosv2.R
import com.example.sistemaalumnosv2.login_screen.data.model.ProviderType
import com.example.sistemaalumnosv2.databinding.FragmentSignInBinding
import com.example.sistemaalumnosv2.login_screen.ui.view.activity.LoginActivity
import com.example.sistemaalumnosv2.menu_screen.ui.view.activity.MenuActivity
import com.example.sistemaalumnosv2.login_screen.ui.viewmodel.ViewModelSignIn
import com.example.sistemaalumnosv2.vo.Resource
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment() {

    private var _binding : FragmentSignInBinding? = null
    private val binding get() = _binding!!

    private val viewModelSignIn : ViewModelSignIn by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.btnSignIn.setOnClickListener {
            observerSignIn()
        }

        toCreateAccountFragment()

        checkGoogleAccount()
    }

    private fun toCreateAccountFragment(){
        binding.tvCreateAccount.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }
    }


    private fun observerSignIn() {
        val email = binding.etEmailSignIn.text.toString()
        val password = binding.etPasswordSignIn.text.toString()

        if (email.isEmpty() || !isValidEmail(email)){
            binding.etEmailSignIn.error = getString(R.string.helperErrorEmail)
        }else if (password.isEmpty()){
            binding.etPasswordSignIn.error = getString(R.string.helperErrorPassword)
        }else{
            viewModelSignIn.signInWithEmail(email, password)

            viewModelSignIn.isLoading.observe(viewLifecycleOwner){
                binding.pbSignIn.isVisible = it
            }

            viewModelSignIn.userModel.observe(viewLifecycleOwner){result ->
                when(result){
                    is Resource.Success ->{
                        navigateToMainMenu(email, ProviderType.EMAIL)
                    }
                    is Resource.Failure ->{
                        Toast.makeText(activity as LoginActivity, "Error al iniciar sesion", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            viewModelSignIn.userException.observe(viewLifecycleOwner){
                Log.e("Error SingIn","$it")
            }
        }
    }

    private fun checkGoogleAccount(){

        val signInLauncher = registerForActivityResult(FirebaseAuthUIActivityResultContract()) { res ->
            this.onSignInResult(res)
        }
        // Choose authentication providers
        val providers = arrayListOf(
            AuthUI.IdpConfig.GoogleBuilder().build()
        )

        // Create and launch sign-in intent
        binding.btnSignUpGoogle.setOnClickListener {
            val signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build()
            signInLauncher.launch(signInIntent)
        }
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        result.idpResponse
        if (result.resultCode == RESULT_OK) {
            // Successfully signed in
            FirebaseAuth.getInstance().currentUser
            val intent = Intent(activity as LoginActivity, MenuActivity::class.java)
            startActivity(intent)
        }else{
            Toast.makeText(activity as LoginActivity, "Error", Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigateToMainMenu(email: String, provider : ProviderType){
        val intent =  Intent(activity as LoginActivity, MenuActivity::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(intent)
    }

    private fun isValidEmail(email: String):Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}