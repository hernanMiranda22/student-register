package com.example.sistemaalumnosv2.menu_screen.ui.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sistemaalumnosv2.R
import com.example.sistemaalumnosv2.menu_screen.data.model.DataStudent

import com.example.sistemaalumnosv2.databinding.CardViewStudentBinding

class OperationAdapter(private var student : MutableList<DataStudent>, context:Context): RecyclerView.Adapter<OperationAdapter.ViewHolder>() {

    private var onClickListener: OnClickListener? = null

    fun setOnClickListener(onClickListener: OnClickListener){
        this.onClickListener = onClickListener
    }

    interface OnClickListener{
        fun onClick(position: Int, gradeStudent: Int)
    }

    inner class ViewHolder(view : View): RecyclerView.ViewHolder(view){
        private val binding = CardViewStudentBinding.bind(view)

        @SuppressLint("SetTextI18n")
        fun bind(student : DataStudent){
            val averageGrade : Float = ((student.firstTerm + student.secondTerm + student.thirdTerm) / 3).toFloat()
            binding.tvDni.text = "DNI: "+student.dni.toString()
            binding.tvSurname.text = "Apellido:" +student.surname
            binding.tvName.text = " Nombre: "+student.name
            binding.tvYear.text = " Grado: "+student.year
            binding.tvFirstTermStudent.text = "1° Trim: "+student.firstTerm.toString()
            binding.tvSecondTermStudent.text = " 2° Trim: "+student.secondTerm.toString()
            binding.tvThirdTermStudent.text = " 3° Trim: "+student.thirdTerm.toString()
            binding.tvAverageGrade.text = "Prom. Final: $averageGrade"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view_student, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val student = student[position]
        holder.bind(student)
    }

    override fun getItemCount(): Int {
        return if (student.size >0){
            student.size
        }else{
            return 0
        }
    }

    fun filterStudent(student : MutableList<DataStudent>){
        this.student = student
        notifyDataSetChanged()
    }
}