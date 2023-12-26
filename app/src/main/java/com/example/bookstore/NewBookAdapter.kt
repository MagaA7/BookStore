package com.example.bookstore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
//kkkk

class NewBookAdapter(var content : List<NewBooks>) : RecyclerView.Adapter<NewBookAdapter.ViewHolder>() {
    inner class ViewHolder(itemView : View):RecyclerView.ViewHolder(itemView){
        val image : ImageView = itemView.findViewById(R.id.imageView_newBooks)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewBookAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewBookAdapter.ViewHolder, position: Int) {
        val current = content[position]
        Picasso.get().load(current.image).into(holder.image)

        holder.image.setOnClickListener {
            if (position != RecyclerView.NO_POSITION) {

                it.findNavController().navigate(
                    R.id.action_homeFragment_to_infoFragment,
                    bundleOf(
                        "newBook_title" to current.title,
                        "newBook_image" to current.image,
                        "newBook_price" to current.price,
                        "newBook_url" to current.url
                    )
                )
            }
        }
    }
    override fun getItemCount(): Int {
        return content.size
    }
}