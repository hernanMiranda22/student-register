package com.example.sistemaalumnosv2.view.adapter

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
import com.example.sistemaalumnosv2.databinding.CardViewBinding
import com.example.sistemaalumnosv2.model.DataStudent

class OperationAdapter(private  val item : Int ,private val context:Context, val callBackText: CallBackText): RecyclerView.Adapter<OperationAdapter.ViewHolder>() {

    private var onClickListener: OnClickListener? = null


    private var dataList = mutableListOf<DataStudent>() //Los datos que se le pasan al onBindViewHolder para que setee los datos

    //Funcion que setea los datos en el dataList
    fun setListData(data:MutableList<DataStudent>){
        dataList = data
    }

    fun setOnClickListener(onClickListener: OnClickListener){
        this.onClickListener = onClickListener
    }

    interface OnClickListener{
        fun onClick(position: Int, gradeStudent: Int)
    }

    inner class ViewHolder(view : View): RecyclerView.ViewHolder(view){
        private val binding = CardViewBinding.bind(view)

        val gradeStudent :EditText = binding.etGradeAdd

        val btnAddGrade = binding.btnGradeAdd
        fun bind(student : DataStudent){
            binding.tvSurname.text = student.surname
            binding.tvYear.text = student.year
            binding.tvGrade.text = student.grade.toString()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OperationAdapter.ViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: OperationAdapter.ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val student = dataList[position]

        holder.bind(student)

        holder.btnAddGrade.setOnClickListener {
            if (onClickListener != null) {
                onClickListener!!.onClick(position, item)
            }
        }

        holder.gradeStudent.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                callBackText.textChangeExercise(position,s.toString())
            }
        })
    }

    override fun getItemCount(): Int {
        return if (dataList.size >0){
            dataList.size
        }else{
            return 0
        }
    }
}