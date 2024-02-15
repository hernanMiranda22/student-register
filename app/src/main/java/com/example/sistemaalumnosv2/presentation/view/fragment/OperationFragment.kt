package com.example.sistemaalumnosv2.presentation.view.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sistemaalumnosv2.data.model.DataStudent
import com.example.sistemaalumnosv2.databinding.FragmentOperationBinding
import com.example.sistemaalumnosv2.presentation.view.activity.MenuActivity
import com.example.sistemaalumnosv2.presentation.view.adapter.OperationAdapter
import com.example.sistemaalumnosv2.presentation.viewmodel.operation.ViewModelOperation
import com.example.sistemaalumnosv2.vo.Resource
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class OperationFragment : Fragment() {

    private var _binding : FragmentOperationBinding? = null
    private val binding get() = _binding!!

    private val viewModelOperation : ViewModelOperation by viewModels()
//    private val viewModelOperation by lazy { ViewModelProvider(this,
//        ViewModelOperationFactory(SearchStudentUseCaseImpl(SearchStudentRepoImpl()))
//    )[ViewModelOperation::class.java] }

    private lateinit var adapter : OperationAdapter

    private var itemAdapter = mutableListOf<DataStudent>()


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

        loadStudent()

        filterList()

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun loadStudent(){
        val auth = FirebaseAuth.getInstance()
        viewModelOperation.searchDataStudent(auth.uid.toString()).observe(viewLifecycleOwner) {list ->
            when(list){
                is Resource.Loading -> {
                    binding.piListStudent.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.piListStudent.visibility = View.GONE
                    itemAdapter.clear()
                    itemAdapter.addAll(list.data)
                    adapter.notifyDataSetChanged()
                }
                is Resource.Failure -> {
                    Log.e("ERROR", "${list.exception}")
                }
            }
        }
    }

    private fun filterList(){
        binding.etDniList.addTextChangedListener {
            val filterList = itemAdapter.filter { studentList -> studentList.dni.toString().lowercase().contains(it.toString().lowercase()) }
            adapter.filterStudent(filterList as MutableList<DataStudent>)
        }
    }

    //Inicialización del RecyclerView
    private fun initRecyclerView() {
        adapter = OperationAdapter(itemAdapter, activity as MenuActivity,)
        binding.rvCardStudent.layoutManager = LinearLayoutManager(activity as MenuActivity)
        binding.rvCardStudent.adapter = adapter

        adapter.setOnClickListener(object : OperationAdapter.OnClickListener {

            override fun onClick(position: Int, gradeStudent: Int) {

            }
        })
    }

    private fun hideKeyboard() {
        if (view != null){
            val imm = this.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.viewOperation.windowToken, 0)
        }
    }

}