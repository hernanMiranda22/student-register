package com.example.sistemaalumnosv2.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sistemaalumnosv2.R
import com.example.sistemaalumnosv2.databinding.FragmentOperationBinding
import com.example.sistemaalumnosv2.model.DataStudent
import com.example.sistemaalumnosv2.view.activity.MainActivity
import com.example.sistemaalumnosv2.view.adapter.OperationAdapter
import com.example.sistemaalumnosv2.viewmodel.ViewModelOperation

class OperationFragment : Fragment() {

    private var _binding : FragmentOperationBinding? = null
    private val binding get() = _binding!!

    private val viewModelOperation : ViewModelOperation by viewModels()

    private lateinit var adapter : OperationAdapter
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
            viewModelOperation.searchStudent(dni.toInt()).observe(viewLifecycleOwner, Observer {lista ->
                if (lista != null){
                    Toast.makeText(activity as MainActivity, "Alumno Encontrado", Toast.LENGTH_SHORT).show()
                            adapter.setListData(lista)
                            adapter.notifyDataSetChanged()
                }else{
                    Toast.makeText(activity as MainActivity, "Error", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun initRecyclerView(){
        adapter = OperationAdapter(activity as MainActivity)
        binding.rvCardStudent.layoutManager = LinearLayoutManager(activity as MainActivity)
        binding.rvCardStudent.adapter = adapter
    }

}