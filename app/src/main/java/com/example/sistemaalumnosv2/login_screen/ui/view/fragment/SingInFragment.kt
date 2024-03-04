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
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.fragment.app.viewModels
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
class SingInFragment : Fragment() {

    private var _binding : FragmentSignInBinding? = null
    private val binding get() = _binding!!

    private val viewModelSignIn : ViewModelSignIn by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.btnSignIn.setOnClickListener {
            observerSignIn()
        }

        checkGoogleAccount()
    }

    private fun observerSignIn() {
        val email = binding.etEmailSignIn.text.toString()
        val password = binding.etPasswordSignIn.text.toString()

        if (email.isEmpty() || !isValidEmail(email)){
            binding.etEmailSignIn.error = getString(R.string.helperErrorEmail)
        }else if (password.isEmpty()){
            binding.etPasswordSignIn.error = getString(R.string.helperErrorPassword)
        }else{
            viewModelSignIn.signInWithEmail(email, password).observe(viewLifecycleOwner){result ->
                when(result){
                    is Resource.Loading ->{
                        binding.pbSignIn.visibility = View.VISIBLE
                    }
                    is Resource.Success ->{
                        binding.pbSignIn.visibility = View.GONE
                        navigateToMainMenu(email, ProviderType.EMAIL)
                    }
                    is Resource.Failure ->{
                        Log.e("Error SingIn","${result.exception}")
                    }
                }
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
        val response = result.idpResponse
        if (result.resultCode == RESULT_OK) {
            // Successfully signed in
            val user = FirebaseAuth.getInstance().currentUser
            val intent = Intent(activity as LoginActivity, MenuActivity::class.java)
            startActivity(intent)
        }
    }

//    private fun navigateToSignUpScreen() {
//        (activity as LoginActivity).supportFragmentManager.commit {
//            setReorderingAllowed(true)
//            replace<SignUpFragment>(R.id.fcvLogin)
//        }
//    }

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