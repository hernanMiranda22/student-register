package com.example.sistemaalumnosv2.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sistemaalumnosv2.R
import com.example.sistemaalumnosv2.databinding.CardViewBinding

class OperationAdapter(private val itemList:List<Int>): RecyclerView.Adapter<OperationAdapter.ViewHolder>() {

    inner class ViewHolder(view : View): RecyclerView.ViewHolder(view){
        private val binding = CardViewBinding.bind(view)

        fun bind(){
            binding.tvSurname.text
            binding.tvYear.text
            binding.tvGrade.text
            binding.etGradeAdd.text.toString()
            binding.btnGradeAdd
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OperationAdapter.ViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: OperationAdapter.ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}