package com.example.sistemaalumnosv2.ui.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sistemaalumnosv2.R
import com.example.sistemaalumnosv2.data.model.GradeStudent
import com.example.sistemaalumnosv2.databinding.CardViewTermBinding


class GradeAdapter(context: Context):RecyclerView.Adapter<GradeAdapter.ViewHolder>() {


    private var dataList = mutableListOf<GradeStudent>()

    fun setData(data : MutableList<GradeStudent>){
        dataList = data
    }


    inner class ViewHolder(view : View): RecyclerView.ViewHolder(view){
        private val binding = CardViewTermBinding.bind(view)



        fun bind(gradeStudent: GradeStudent){

            binding.tvSurnameGrade.text = gradeStudent.surname
            binding.tvNameGrade.text = gradeStudent.name
            binding.tvYearGrade.text = gradeStudent.year
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GradeAdapter.ViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view_term, parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: GradeAdapter.ViewHolder, position: Int) {
        val data = dataList[position]

       holder.bind(data)
    }

    override fun getItemCount(): Int {
        return if (dataList.size > 0 ){
            dataList.size
        }else{
            0
        }
    }
}