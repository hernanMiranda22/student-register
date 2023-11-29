package com.example.sistemaalumnosv2.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.sistemaalumnosv2.R
import com.example.sistemaalumnosv2.databinding.FragmentStudenDataBinding
import com.example.sistemaalumnosv2.view.activity.MainActivity
import com.example.sistemaalumnosv2.viewmodel.ViewModelStudent
import com.google.android.material.textfield.MaterialAutoCompleteTextView

class StudentDataFragment : Fragment() {


    private var _binding : FragmentStudenDataBinding? = null
    private val binding get() = _binding!!

    private val viewModelStudent : ViewModelStudent by viewModels()

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
        }

        binding.btnSaveData.setOnClickListener {
            createStudent()
        }

    }

    private fun createStudent(){

        val dni = binding.etDni.text.toString()
        val name = binding.etName.text.toString()
        val surname = binding.etSurname.text.toString()
        val year = _year

        if (dni.isEmpty() || dni.length < 8){
            binding.etDni.error = getString(R.string.helperError)
        }else if (name.isEmpty() || surname.length < 3){
            binding.etName.error = getString(R.string.helperError)
        }else if (surname.isEmpty() || surname.length < 4){
            binding.etSurname.error = getString(R.string.helperError)
        }else if (year.isEmpty()){
            binding.acYear.error = getString(R.string.helperError)
        }else{
            viewModelStudent.createStudent(dni.toInt(),name,surname, year).observe(viewLifecycleOwner, Observer {
                if (it != null){
                    Toast.makeText(activity as MainActivity, "Alumno ingresado!", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(activity as MainActivity, "Error al ingresar el alumno", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun getYear(){
        binding.acYear.setOnItemClickListener { parent, view, position, id ->
            val studentSelected = parent.getItemAtPosition(position) as String
            _year = studentSelected
        }
    }

    private fun errorInputText(){
        // Set error text
        val txtName : EditText? = view?.findViewById(R.id.etName)
        txtName?.error = getString(R.string.helperError)

        // Clear error text
        txtName?.error = null
    }

    //Inflate Drop Menu
    private fun dropMenu(){
        val autoCompleteTextView: AutoCompleteTextView? = view?.findViewById(R.id.acYear)

        val items = listOf("4.1", "5.1", "6.1")

        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, items)

        (autoCompleteTextView)?.setAdapter(adapter)
    }
}