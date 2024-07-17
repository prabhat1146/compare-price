package com.yogeshj.pricecomparison.Activity.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.yogeshj.pricecomparison.Activity.Model.ItemsModel
import com.yogeshj.pricecomparison.databinding.ViewholderBestSellerBinding

class BestSellerAdapter (val items: MutableList<ItemsModel>):
        RecyclerView.Adapter<BestSellerAdapter.Viewholder>(){
            private var context: Context? = null
        class Viewholder(val binding : ViewholderBestSellerBinding):
                RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestSellerAdapter.Viewholder {
       context = parent.context
        val binding =
            ViewholderBestSellerBinding.inflate(LayoutInflater.from(context),parent,false)
        return Viewholder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
       holder.binding.titleTxt.text = items[position].title
        holder.binding.priceTxt.text = "$"+items[position].price.toString()
        holder.binding.ratingTxt.text = items[position].rating.toString()

        val requiresOption=RequestOptions().transform(CenterCrop())
        Glide.with(holder.itemView.context)
            .load(items[position].picUrl[0])
            .apply(requiresOption)
            .into(holder.binding.picBestSeller)
    }


}