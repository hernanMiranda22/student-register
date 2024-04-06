package com.example.sistemaalumnosv2.menu_screen.ui.view.fragment

import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.sistemaalumnosv2.R
import com.example.sistemaalumnosv2.databinding.FragmentStudenDataBinding
import com.example.sistemaalumnosv2.login_screen.ui.view.activity.LoginActivity
import com.example.sistemaalumnosv2.menu_screen.ui.view.activity.MenuActivity
import com.example.sistemaalumnosv2.menu_screen.ui.viewmodel.studentviewmodel.ViewModelStudent
import com.example.sistemaalumnosv2.vo.Resource
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class StudentDataFragment : Fragment() {

    //Declaracion del ViewBingin
    private var _binding : FragmentStudenDataBinding? = null
    private val binding get() = _binding!!

    //Declaracion del ViewModel
    private val viewModelStudent : ViewModelStudent by viewModels()

    private var _year = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentStudenDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        validateText()

        dropMenu()

        getYear()

        binding.acYear.setOnClickListener {
            binding.acYear.error = null
            dropMenu()
        }

        binding.btnSaveData.setOnClickListener {
            observeInsert()
        }

        binding.btnSignOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(activity as MenuActivity, LoginActivity::class.java)
            startActivity(intent)
            (activity as MenuActivity).finish()
        }
    }

    //Obtiene el valor del DropMenu y lo guarda en la variable '_year'
    private fun getYear(){
        binding.acYear.setOnItemClickListener { parent, _, position, _ ->
            val studentSelected = parent.getItemAtPosition(position) as String
            _year = studentSelected
        }
    }

    //Inflate Drop Menu
    private fun dropMenu(){
        val autoCompleteTextView: AutoCompleteTextView? = view?.findViewById(R.id.acYear)

        val items = listOf("4.1", "5.1", "6.1")

        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, items)

        (autoCompleteTextView)?.setAdapter(adapter)
    }


    //Logica para ingresar los datos del alumno con un Oberver
    private fun observeInsert() {

        val auth = FirebaseAuth.getInstance()

        val dni = binding.etDni.text.toString()
        val name = binding.etName.text.toString()
        val surname = binding.etSurname.text.toString()
        val year = _year

        if (dni.isEmpty() || dni.length < 8) {
            binding.etDni.error = getString(R.string.helperErrorDni)
        } else if (name.isEmpty() || surname.length < 3) {
            binding.etName.error = getString(R.string.helperErrorName)
        } else if (surname.isEmpty() || surname.length < 4) {
            binding.etSurname.error = getString(R.string.helperErrorSurname)
        } else if (year.isEmpty()) {
            binding.acYear.error = getString(R.string.helperErrorYear)
        } else {
            viewModelStudent.insertNewStudent(dni.toInt(), name, surname, year, auth.uid.toString())

            viewModelStudent.isLoading.observe(viewLifecycleOwner){
                binding.piInsert.isVisible = it
            }

            viewModelStudent.studentModel.observe(viewLifecycleOwner) { result ->
                when (result) {
                    is Resource.Success -> {
                        Toast.makeText(activity as MenuActivity, "Alumno ingresado exitosamente", Toast.LENGTH_SHORT).show()
                        clearText()
                    }

                    is Resource.Failure -> {
                        Toast.makeText(activity as MenuActivity, "Error al ingresar al Alumno", Toast.LENGTH_SHORT).show()
                    }
                }

                viewModelStudent.exception.observe(viewLifecycleOwner){
                    Log.e("Error Student","$it")
                }
            }
        }
    }

    private fun clearText(){
        val dni = binding.etDni
        val name = binding.etName
        val surname = binding.etSurname
        dni.setText("")
        name.setText("")
        surname.setText("")
    }

    private fun validateText(){

        val nameEditText = binding.etName
        val surnameEditText = binding.etSurname

        val filter = InputFilter { source, start, end, _, _, _ ->
            for (i in start until end) {
                if (!Character.isLetterOrDigit(source[i])) {
                    return@InputFilter ""
                }
            }
            null
        }

        nameEditText.filters = arrayOf(filter)
        surnameEditText.filters = arrayOf(filter)
    }

}