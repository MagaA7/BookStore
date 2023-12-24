package com.example.bookstore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class NewBookAdapter(var content : List<String>) : RecyclerView.Adapter<NewBookAdapter.ViewHolder>() {
    inner class ViewHolder(itemView : View):RecyclerView.ViewHolder(itemView){
        val image : ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewBookAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewBookAdapter.ViewHolder, position: Int) {
        val current = content[position]

    }
    override fun getItemCount(): Int {
        return content.size
    }

    fun updateData(newContent: List<String>) {
        content = newContent
        notifyDataSetChanged()
    }
}