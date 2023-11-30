package com.example.sistemaalumnosv2.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sistemaalumnosv2.R
import com.example.sistemaalumnosv2.databinding.CardViewBinding
import com.example.sistemaalumnosv2.model.DataStudent

class OperationAdapter(private val context:Context): RecyclerView.Adapter<OperationAdapter.ViewHolder>() {

    private var dataList = mutableListOf<DataStudent>() //Los datos que se le pasan al onBindViewHolder para que setee los datos

    //Funcion que setea los datos en el dataList
    fun setListData(data:MutableList<DataStudent>){
        dataList = data
    }

    inner class ViewHolder(view : View): RecyclerView.ViewHolder(view){
        private val binding = CardViewBinding.bind(view)

        fun bind(student : DataStudent){
            binding.tvSurname.text = student.surname
            binding.tvYear.text = student.year
            binding.tvGrade.text = student.grade.toString()
            binding.etGradeAdd.text.toString()
            binding.btnGradeAdd.setOnClickListener {  }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OperationAdapter.ViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: OperationAdapter.ViewHolder, position: Int) {
        val student = dataList[position]

        holder.bind(student)
    }

    override fun getItemCount(): Int {
        return if (dataList.size >0){
            dataList.size
        }else{
            return 0
        }
    }
}