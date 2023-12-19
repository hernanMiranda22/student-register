package com.example.sistemaalumnosv2.presentation.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.example.sistemaalumnosv2.R
import com.example.sistemaalumnosv2.data.model.DataStudent
import com.example.sistemaalumnosv2.data.model.GradeStudent
import com.example.sistemaalumnosv2.databinding.CardViewStudentBinding

class OperationAdapter(private  val item : Int ,private val context:Context): RecyclerView.Adapter<OperationAdapter.ViewHolder>() {

    private var onClickListener: OnClickListener? = null


    private var dataList = mutableListOf<GradeStudent>() //Los datos que se le pasan al onBindViewHolder para que setee los datos

    //Funcion que setea los datos en el dataList
    fun setListData(data:MutableList<GradeStudent>){
        dataList = data
    }

    fun setOnClickListener(onClickListener: OnClickListener){
        this.onClickListener = onClickListener
    }

    interface OnClickListener{
        fun onClick(position: Int, gradeStudent: Int)
    }

    inner class ViewHolder(view : View): RecyclerView.ViewHolder(view){
        private val binding = CardViewStudentBinding.bind(view)

        val btnAddGrade = binding.btnGradeAdd
        fun bind(student : GradeStudent){
            val averageGrade : Float = ((student.firstTerm + student.secondTerm + student.thirdTerm) / 3).toFloat()
            binding.tvSurname.text = student.surname
            binding.tvName.text = student.name
            binding.tvYear.text = student.year
            binding.tvFirstTermStudent.text = student.firstTerm.toString()
            binding.tvSecondTermStudent.text = student.secondTerm.toString()
            binding.tvThirdTermStudent.text = student.thirdTerm.toString()
            binding.tvAverageGrade.text = averageGrade.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view_student, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val student = dataList[position]

        holder.bind(student)

        holder.btnAddGrade.setOnClickListener {
            if (onClickListener != null) {
                onClickListener!!.onClick(position, item)
            }
        }
    }

    override fun getItemCount(): Int {
        return if (dataList.size >0){
            dataList.size
        }else{
            return 0
        }
    }
}