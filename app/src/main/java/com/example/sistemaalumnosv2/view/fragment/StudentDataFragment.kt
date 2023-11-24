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
import com.example.sistemaalumnosv2.R
import com.google.android.material.textfield.MaterialAutoCompleteTextView

class StudentDataFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_studen_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        dropMenu()
        errorInputText()
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
        val autoCompleteTextView: AutoCompleteTextView? = view?.findViewById(R.id.autoCompleteTextView)

        val items2 = listOf("Item 1", "Item 2", "Item 3", "Item 4")

        val adapter2 = ArrayAdapter(requireContext(), R.layout.list_item, items2)

        (autoCompleteTextView)?.setAdapter(adapter2)
    }
}