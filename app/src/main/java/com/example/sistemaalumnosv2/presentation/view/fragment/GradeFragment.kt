package com.example.sistemaalumnosv2.presentation.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintSet
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sistemaalumnosv2.R
import com.example.sistemaalumnosv2.data.model.GradeStudent
import com.example.sistemaalumnosv2.data.network.GradeStudentRepoImpl
import com.example.sistemaalumnosv2.data.network.SearchGradeRepoImpl
import com.example.sistemaalumnosv2.databinding.FragmentGradeBinding
import com.example.sistemaalumnosv2.domain.GradeStudentUseCaseImpl
import com.example.sistemaalumnosv2.domain.SearchGradeUseCaseImpl
import com.example.sistemaalumnosv2.presentation.view.activity.MainActivity
import com.example.sistemaalumnosv2.presentation.view.adapter.GradeAdapter
import com.example.sistemaalumnosv2.presentation.viewmodel.ViewModelGrade
import com.example.sistemaalumnosv2.presentation.viewmodel.ViewModelGradeFactory
import com.example.sistemaalumnosv2.vo.Resource


class GradeFragment : Fragment() {

    private var _binding: FragmentGradeBinding? = null
    private val binding get() = _binding!!

    private val viewModelGrade : ViewModelGrade by lazy {
        ViewModelProvider(this, ViewModelGradeFactory(SearchGradeUseCaseImpl(SearchGradeRepoImpl()),
            GradeStudentUseCaseImpl(GradeStudentRepoImpl())))[ViewModelGrade::class.java]
    }

    private lateinit var gradeAdapter: GradeAdapter
    private val contentRecycler = mutableListOf<GradeStudent>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        _binding = FragmentGradeBinding.inflate(layoutInflater,container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        doneKeyboard(binding.etDniGrade)

        initRecyclerView()


        binding.tiContainerDniGrade.setEndIconOnClickListener {
            getStudentData()
        }

        binding.btnInsertTerm.setOnClickListener {
            insertTerm()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getStudentData() {
        val editTextFirstTerm = binding.etFirstTerm
        val editTextSecondTerm = binding.etSecondTerm
        val editTextThirdTerm = binding.etThirdTerm
        val dni =  binding.etDniGrade.text.toString()

        if (dni.isEmpty()){
            binding.etDniGrade.error = getString(R.string.helperErrorDni)
        }else{
            viewModelGrade.getDataAndTerm(dni.toInt()).observe(viewLifecycleOwner){ result ->
                when(result){
                    is Resource.Loading -> {
                        binding.rvStudentData.visibility = View.GONE
                        showProgressBar()
                        setBias(0.15f)
                    }
                    is Resource.Success -> {
                        hideProgressBar()
                        setBias(0.05f)
                        contentRecycler.clear()
                        gradeAdapter.setData(result.data)
                        gradeAdapter.notifyDataSetChanged()
                        binding.rvStudentData.visibility = View.VISIBLE
                        for (term in result.data){
                            editTextFirstTerm.setText(term.firstTerm.toString())
                            editTextSecondTerm.setText(term.secondTerm.toString())
                            editTextThirdTerm.setText(term.thirdTerm.toString())
                        }
                    }
                    is Resource.Failure -> {
                        Log.e("ERROR DATA", "${result.exception}")
                    }
                }

            }
        }
    }

    private fun insertTerm(){
        val firstTerm = binding.etFirstTerm.text.toString()
        val secondTerm = binding.etSecondTerm.text.toString()
        val thirdTerm = binding.etThirdTerm.text.toString()
        val dni =  binding.etDniGrade.text.toString()

        if (firstTerm.isEmpty() || firstTerm.toInt() == 0){
            binding.etFirstTerm.error = getString(R.string.helperErrorGrade)
        }else if (secondTerm.isEmpty() || secondTerm.toInt() == 0){
            binding.etSecondTerm.error = getString(R.string.helperErrorGrade)
        }else if (thirdTerm.isEmpty() || thirdTerm.toInt() == 0){
            binding.etThirdTerm.error = getString(R.string.helperErrorGrade)
        }else{
            viewModelGrade.insertGrade(dni.toInt(), firstTerm.toInt(), secondTerm.toInt(),  thirdTerm.toInt()).observe(viewLifecycleOwner){result ->
                when(result){
                    is Resource.Loading ->{

                    }
                    is Resource.Success -> {
                        Toast.makeText(activity as MainActivity, "Trimestres ingresados!", Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Failure ->{
                        Log.e("ERRO INSERT", "${result.exception}")
                    }
                }
            }
        }


    }
    @SuppressLint("NotifyDataSetChanged")
    private fun initRecyclerView(){
        gradeAdapter = GradeAdapter(activity as MainActivity)
        binding.rvStudentData.layoutManager = LinearLayoutManager(activity as MainActivity)
        binding.rvStudentData.adapter = gradeAdapter


        contentRecycler.add(GradeStudent("Nombre","Apellido" ,"Grado", 0, 0,0))

        gradeAdapter.setData(contentRecycler)
        gradeAdapter.notifyDataSetChanged()
    }

    private fun hideProgressBar() {
        binding.piTerm.visibility = View.GONE
    }


    private fun showProgressBar() {
        binding.piTerm.visibility = View.VISIBLE
    }

    private fun setBias(bias : Float){
        val constrainGrade = binding.constrainGrade
        val constrainSet = ConstraintSet()

        constrainSet.clone(constrainGrade)
        constrainSet.setVerticalBias(R.id.tiContainerFirstTerm, bias)
        constrainSet.applyTo(constrainGrade)
    }

    private fun doneKeyboard(search: EditText){
        search.setOnEditorActionListener(TextView.OnEditorActionListener{ _, actionId, _ ->

            if (actionId == EditorInfo.IME_ACTION_DONE) {

                // Do something of your interest.
                // We in this examples created the following Toasts
                if(search.text.toString() == ""){
                    binding.etDniGrade.error = getString(R.string.helperErrorDni)
                } else {
                    getStudentData()
                }

                return@OnEditorActionListener true
            }
            false
        })
    }


}