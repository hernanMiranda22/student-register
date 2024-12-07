package com.example.sistemaalumnosv2.menu_screen.ui.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sistemaalumnosv2.menu_screen.data.model.DataStudent
import com.example.sistemaalumnosv2.databinding.FragmentOperationBinding
import com.example.sistemaalumnosv2.menu_screen.ui.model.DataStudentUI
import com.example.sistemaalumnosv2.menu_screen.ui.model.ResourceMenu
import com.example.sistemaalumnosv2.menu_screen.ui.view.activity.MenuActivity
import com.example.sistemaalumnosv2.menu_screen.ui.view.adapter.OperationAdapter
import com.example.sistemaalumnosv2.menu_screen.ui.viewmodel.operation.ViewModelOperation
import com.example.sistemaalumnosv2.vo.Resource
import com.example.sistemaalumnosv2.vo.UserSingleton
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class OperationFragment : Fragment() {

    private var _binding : FragmentOperationBinding? = null
    private val binding get() = _binding!!

    private val viewModelOperation : ViewModelOperation by viewModels()

    private lateinit var adapter : OperationAdapter

    private var itemAdapter = mutableListOf<DataStudentUI>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOperationBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        loadStudent()

        filterList()

        binding.srContainer.setOnRefreshListener {
            loadStudent()
            binding.srContainer.isRefreshing = false
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun loadStudent(){

        viewModelOperation.searchDataStudent(UserSingleton.getUserId())

        viewModelOperation.isLoading.observe(viewLifecycleOwner){
            binding.piListStudent.isVisible = it
        }

        viewModelOperation.studentDataModel.observe(viewLifecycleOwner) {list ->
            when(list){
                is ResourceMenu.Failure -> {
                    Toast.makeText(activity as MenuActivity, "Error al obtener los estudiantes", Toast.LENGTH_SHORT).show()
                }
                ResourceMenu.Loading -> {
                    binding.piListStudent.visibility = View.VISIBLE
                }
                is ResourceMenu.Success -> {
                    binding.piListStudent.visibility = View.GONE
                    itemAdapter.clear()
                    itemAdapter.addAll(list.data)
                    adapter.notifyDataSetChanged()
                }
            }
        }

//        val auth = FirebaseAuth.getInstance()
//
//        lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED){
//                viewModelOperation.listStudent.collect{ uiState ->
//                    when(uiState){
//                        is ResourceMenu.Failure -> {
//                            Toast.makeText(activity as MenuActivity, "Error al obtener los estudiantes", Toast.LENGTH_SHORT).show()
//                        }
//                        ResourceMenu.Loading -> {
//                            binding.piListStudent.visibility = View.VISIBLE
//                        }
//                        is ResourceMenu.Success -> {
//                            binding.piListStudent.visibility = View.GONE
//                            itemAdapter.clear()
//                            itemAdapter.addAll(uiState.data)
//                            adapter.notifyDataSetChanged()
//                        }
//                    }
//                }
//            }
//        }
//
//        viewModelOperation.getDataStudent(uid = auth.uid.toString())

    }

//    @SuppressLint("NotifyDataSetChanged")
//    private fun reLoadStudent(){
//        val auth = FirebaseAuth.getInstance()
//
//        lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED){
//                viewModelOperation.listStudent.collect{uiState ->
//                    when(uiState){
//                        is ResourceMenu.Failure -> {
//                            Toast.makeText(activity as MenuActivity, "Error al refrescar", Toast.LENGTH_SHORT).show()
//                        }
//                        ResourceMenu.Loading -> {
//
//                        }
//                        is ResourceMenu.Success -> {
//                            itemAdapter.clear()
//                            itemAdapter.addAll(uiState.data)
//                            adapter.notifyDataSetChanged()
//                        }
//                    }
//
//                }
//            }
//        }
//    }

    private fun filterList(){
        binding.etDniList.addTextChangedListener {
            val filterList = itemAdapter.filter { studentList -> studentList.dni.toString().lowercase().contains(it.toString().lowercase()) }
            adapter.filterStudent(filterList as MutableList<DataStudentUI>)
        }
    }

    //Inicialización del RecyclerView
    private fun initRecyclerView() {
        adapter = OperationAdapter(itemAdapter, activity as MenuActivity)
        binding.rvCardStudent.layoutManager = LinearLayoutManager(activity as MenuActivity)
        binding.rvCardStudent.adapter = adapter

        adapter.setOnClickListener(object : OperationAdapter.OnClickListener {

            override fun onClick(position: Int, gradeStudent: Int) {

            }
        })
    }

}