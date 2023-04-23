package com.hdh.mycafe.ui.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hdh.mycafe.databinding.AddCafeListBinding
import com.hdh.mycafe.ui.fragment.AddCafeFragment

class AddCafeAdapter(context: Context) : RecyclerView.Adapter<AddCafeAdapter.CustomViewHolder>(){

    interface ItemClickListener{
        fun onClick(view: View, position: Int)
    }
    lateinit var itemClickListener : ItemClickListener


    inner class CustomViewHolder(binding: AddCafeListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindInfo(pos:Int){
            val position = pos

            itemView.setOnClickListener{
                itemClickListener.onClick(it, layoutPosition)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding : AddCafeListBinding = AddCafeListBinding.inflate(LayoutInflater.from(parent.context))

        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bindInfo(position)
    }

    override fun getItemCount(): Int {
        return 10
    }

}