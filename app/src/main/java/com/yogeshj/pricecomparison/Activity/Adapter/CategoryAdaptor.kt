package com.yogeshj.pricecomparison.Activity.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.yogeshj.pricecomparison.Activity.Model.CategoryModel
import com.yogeshj.pricecomparison.databinding.ViewholderCategoryBinding

class CategoryAdaptor(val items:MutableList<CategoryModel>):
    RecyclerView.Adapter<CategoryAdaptor.ViewHolder>(){
        private lateinit var context:Context

        inner class ViewHolder(val binding: ViewholderCategoryBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdaptor.ViewHolder {
        context= parent.context
        val binding=ViewholderCategoryBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryAdaptor.ViewHolder, position: Int) {
       val item=items[position]
        holder.binding.titleCat.text=item.title

        Glide.with(holder.itemView.context)
            .load(item.picUrl)
            .into(holder.binding.picCat)
    }

    override fun getItemCount(): Int=items.size
    }
