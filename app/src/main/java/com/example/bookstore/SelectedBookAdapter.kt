package com.example.bookstore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class SelectedBookAdapter(var content: MutableList<SelectedBook>) : RecyclerView.Adapter<SelectedBookAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.imageView_basket)
        val title: TextView = itemView.findViewById(R.id.textView_title)
        val price: TextView = itemView.findViewById(R.id.textView_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedBookAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_basket, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: SelectedBookAdapter.ViewHolder, position: Int) {
        val current = content[position]
        Picasso.get().load(current.image).into(holder.image)
        holder.title.text = current.title
        holder.price.text = current.price
    }

    override fun getItemCount(): Int {
        return content.size
    }

    fun update(arrayList: ArrayList<SelectedBook>){
        content.clear()
        content.addAll(arrayList)
        notifyDataSetChanged()
    }

}