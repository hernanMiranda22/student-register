package com.example.sistemaalumnosv2.presentation.view.fragment

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModelProvider
import com.example.sistemaalumnosv2.R
import com.example.sistemaalumnosv2.data.network.InsertStudentRepoImpl
import com.example.sistemaalumnosv2.databinding.FragmentStudenDataBinding
import com.example.sistemaalumnosv2.domain.InsertUseCaseImpl
import com.example.sistemaalumnosv2.presentation.view.activity.MainActivity
import com.example.sistemaalumnosv2.presentation.viewmodel.ViewModelStudent
import com.example.sistemaalumnosv2.presentation.viewmodel.ViewModelStudentFactory
import com.example.sistemaalumnosv2.vo.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class StudentDataFragment : Fragment() {

    //Declaracion del ViewBingin
    private var _binding : FragmentStudenDataBinding? = null
    private val binding get() = _binding!!

    //Declaracion del ViewModel
    private val viewModelStudent by lazy { ViewModelProvider(this,
        ViewModelStudentFactory(InsertUseCaseImpl(InsertStudentRepoImpl())))[ViewModelStudent::class.java] }

    private var _year = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStudenDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dropMenu()

        getYear()

        binding.acYear.setOnClickListener {
            binding.acYear.error = null

            CoroutineScope(Dispatchers.Main).launch {
                hideKeyboard()
                delay(500)
                dropMenu()
            }
        }

        binding.btnSaveData.setOnClickListener {
            observeInsert()
        }


    }

    //Obtiene el valor del DropMenu y lo guarda en la variable '_year'
    private fun getYear(){
        binding.acYear.setOnItemClickListener { parent, view, position, id ->
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

            viewModelStudent.insertNewStudent(dni.toInt(), name, surname, year).observe(viewLifecycleOwner) { result ->
                when (result) {
                    is Resource.Loading -> {
                        showProgress()
                    }

                    is Resource.Success -> {
                        Toast.makeText(activity as MainActivity, "Alumno ingresado exitosamente", Toast.LENGTH_SHORT).show()
                        hideProgress()
                        clearText()
                    }

                    is Resource.Failure -> {
                       Log.e("Erorr","${result.exception}")
                    }
                }
            }
        }
    }


    private fun showProgress(){
        binding.piInsert.visibility = View.VISIBLE
    }

    private fun hideProgress(){
        binding.piInsert.visibility = View.GONE
    }

    private fun hideKeyboard() {
        if (view != null){
            val imm = this.context?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.viewStudent.windowToken, 0)
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
}