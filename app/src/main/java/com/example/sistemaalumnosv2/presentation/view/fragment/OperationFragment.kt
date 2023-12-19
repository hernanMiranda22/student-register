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
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sistemaalumnosv2.R
import com.example.sistemaalumnosv2.data.network.SearchStudentRepoImpl
import com.example.sistemaalumnosv2.databinding.FragmentOperationBinding
import com.example.sistemaalumnosv2.domain.SearchStudentUseCaseImpl
import com.example.sistemaalumnosv2.presentation.view.activity.MainActivity
import com.example.sistemaalumnosv2.presentation.view.adapter.OperationAdapter
import com.example.sistemaalumnosv2.presentation.viewmodel.ViewModelOperation
import com.example.sistemaalumnosv2.presentation.viewmodel.ViewModelOperationFactory
import com.example.sistemaalumnosv2.vo.Resource

class OperationFragment : Fragment() {

    private var _binding : FragmentOperationBinding? = null
    private val binding get() = _binding!!

    private val viewModelOperation by lazy { ViewModelProvider(this,
        ViewModelOperationFactory(SearchStudentUseCaseImpl(SearchStudentRepoImpl())))[ViewModelOperation::class.java] }

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
            binding.etDni.error = getString(R.string.helperErrorDni)
        }else{
            viewModelOperation.searchDataStudent(dni.toInt()).observe(viewLifecycleOwner) {list ->
                when(list){
                    is Resource.Loading -> {
                        binding.shimmerLayout.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.shimmerLayout.stopShimmer()
                        binding.shimmerLayout.visibility = View.GONE
                        adapter.setListData(list.data)
                        adapter.notifyDataSetChanged()
                        hideKeyboard()
                    }
                    is Resource.Failure -> {
                        Log.e("ERROR", "${list.exception}")
                    }
                }
            }
        }
    }

    //Inicialización del RecyclerView
    private fun initRecyclerView() {
        adapter = OperationAdapter(itemAdapter, activity as MainActivity,)
        binding.rvCardStudent.layoutManager = LinearLayoutManager(activity as MainActivity)
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