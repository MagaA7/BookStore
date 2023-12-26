package com.example.bookstore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class SearchAdapter(var content : List<SearchBook>) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    inner class ViewHolder(itemView : View):RecyclerView.ViewHolder(itemView){
        val image : ImageView = itemView.findViewById(R.id.imageView_search)
        val title : TextView = itemView.findViewById(R.id.textView_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchAdapter.ViewHolder, position: Int) {
        val current = content[position]
        Picasso.get().load(current.image).into(holder.image)
        holder.title.text = current.title

        holder.image.setOnClickListener {
            if (position != RecyclerView.NO_POSITION) {

                it.findNavController().navigate(
                    R.id.action_searchFragment_to_infoFragment,
                    bundleOf(
                        "searchBook_title" to current.title,
                        "searchBook_image" to current.image,
                        "searchBook_price" to current.price,
                    )
                )
            }
        }
    }
    override fun getItemCount(): Int {
        return content.size
    }
}