package com.example.sistemaalumnosv2.presentation.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sistemaalumnosv2.R
import com.example.sistemaalumnosv2.data.DataStudent
import com.example.sistemaalumnosv2.data.network.GradeStudentRepoImpl
import com.example.sistemaalumnosv2.data.network.SearchStudentRepoImpl
import com.example.sistemaalumnosv2.databinding.FragmentOperationBinding
import com.example.sistemaalumnosv2.domain.GradeStudentUseCaseImpl
import com.example.sistemaalumnosv2.domain.SearchStudentUseCaseImpl
import com.example.sistemaalumnosv2.presentation.view.activity.MainActivity
import com.example.sistemaalumnosv2.presentation.view.adapter.CallBackText
import com.example.sistemaalumnosv2.presentation.view.adapter.OperationAdapter
import com.example.sistemaalumnosv2.presentation.viewmodel.ViewModelOperation
import com.example.sistemaalumnosv2.presentation.viewmodel.ViewModelOperationFactory
import com.example.sistemaalumnosv2.vo.Resource

class OperationFragment : Fragment() {

    private var _binding : FragmentOperationBinding? = null
    private val binding get() = _binding!!

    private val viewModelOperation by lazy { ViewModelProvider(this,ViewModelOperationFactory(SearchStudentUseCaseImpl(SearchStudentRepoImpl()),
        GradeStudentUseCaseImpl(GradeStudentRepoImpl())))[ViewModelOperation::class.java] }

    private lateinit var adapter : OperationAdapter

    private var itemAdapter = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOperationBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        binding.tiContainerDni.setEndIconOnClickListener {
            searchStudent()
        }

    }


    @SuppressLint("NotifyDataSetChanged")
    private fun searchStudent(){
        val dni = binding.etDni.text.toString()

        if (dni.isEmpty() || dni.length < 8){
            binding.etDni.error = getString(R.string.helperError)
        }else{
            viewModelOperation.searchDataStudent(dni.toInt()).observe(viewLifecycleOwner) {lista ->
                when(lista){
                    is Resource.Loading -> {
                        binding.shimmerLayout.startShimmer()
                    }
                    is Resource.Success -> {
                        binding.shimmerLayout.stopShimmer()
                        binding.shimmerLayout.hideShimmer()
                        adapter.setListData(lista.data)
                        adapter.notifyDataSetChanged()
                    }
                    is Resource.Failure -> {
                        Log.e("ERROR", "${lista.exception}")
                    }
                }
            }
        }
    }

    private fun initRecyclerView() {
        adapter = OperationAdapter(itemAdapter, activity as MainActivity, object : CallBackText {
            //Obtiene el contenido del EditText del RecyclerView después de que cambie su valor.
            override fun textChangeExercise(position: Int, grade: String) {
                itemAdapter = grade.toInt()

                //Después de obtener el valor del EditText, se llama al onClick del boton "Ingresar nota" y se actualiza el campo "Nota" en FireStore
                adapter.setOnClickListener(object : OperationAdapter.OnClickListener {
                    override fun onClick(position: Int, gradeStudent: Int) {
                        val dni = binding.etDni.text.toString()

                        viewModelOperation.insertGrade(dni.toInt(),itemAdapter)
                            .observe(viewLifecycleOwner, Observer {result ->
                                if(result == null){
                                    Toast.makeText(activity as MainActivity, "Error al ingresar la nota", Toast.LENGTH_SHORT).show()
                                }else{
                                    Toast.makeText(activity as MainActivity, "Nota ingresada", Toast.LENGTH_SHORT).show()
                                }
                            })
                    }
                })
            }
        })
        binding.rvCardStudent.layoutManager = LinearLayoutManager(activity as MainActivity)
        binding.rvCardStudent.adapter = adapter
    }
}