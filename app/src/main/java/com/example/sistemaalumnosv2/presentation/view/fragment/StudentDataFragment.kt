package com.example.sistemaalumnosv2.presentation.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sistemaalumnosv2.R
import com.example.sistemaalumnosv2.data.network.InsertStudentRepoImpl
import com.example.sistemaalumnosv2.databinding.FragmentStudenDataBinding
import com.example.sistemaalumnosv2.domain.InsertUseCaseImpl
import com.example.sistemaalumnosv2.presentation.view.activity.MainActivity
import com.example.sistemaalumnosv2.presentation.viewmodel.ViewModelStudent
import com.example.sistemaalumnosv2.presentation.viewmodel.ViewModelStudentFactory
import com.example.sistemaalumnosv2.vo.Resource


class StudentDataFragment : Fragment() {


    private var _binding : FragmentStudenDataBinding? = null
    private val binding get() = _binding!!

    //private val viewModelStudent : ViewModelStudent by viewModels()

    private val viewModelStudent by lazy { ViewModelProvider(this, ViewModelStudentFactory(InsertUseCaseImpl(InsertStudentRepoImpl())))[ViewModelStudent::class.java] }

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
            observeInsert()
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

    //Inflate Drop Menu
    private fun dropMenu(){
        val autoCompleteTextView: AutoCompleteTextView? = view?.findViewById(R.id.acYear)

        val items = listOf("4.1", "5.1", "6.1")

        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, items)

        (autoCompleteTextView)?.setAdapter(adapter)
    }


    private fun observeInsert() {

        val dni = binding.etDni.text.toString()
        val name = binding.etName.text.toString()
        val surname = binding.etSurname.text.toString()
        val year = _year

        if (dni.isEmpty() || dni.length < 8) {
            binding.etDni.error = getString(R.string.helperError)
        } else if (name.isEmpty() || surname.length < 3) {
            binding.etName.error = getString(R.string.helperError)
        } else if (surname.isEmpty() || surname.length < 4) {
            binding.etSurname.error = getString(R.string.helperError)
        } else if (year.isEmpty()) {
            binding.acYear.error = getString(R.string.helperError)
        } else {



            viewModelStudent.insertNewStudent(dni.toInt(), name, surname, year).observe(viewLifecycleOwner) { result ->
                when (result) {
                    is Resource.Loading -> {
                        showProgress()
                    }

                    is Resource.Success -> {
                        Toast.makeText(activity as MainActivity, "Alumno ingresado exitosamente", Toast.LENGTH_SHORT).show()
                        hideProgress()
                    }

                    is Resource.Failure -> {
                        Toast.makeText(activity as MainActivity, "Error al ingresar ${result.exception}", Toast.LENGTH_SHORT).show()
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
}