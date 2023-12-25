package com.example.bookstore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class KotlinBookAdapter(var content : List<KotlinBooks>) : RecyclerView.Adapter<KotlinBookAdapter.ViewHolder>() {
    inner class ViewHolder(itemView : View):RecyclerView.ViewHolder(itemView){
        val image : ImageView = itemView.findViewById(R.id.imageView_kotlinBooks)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KotlinBookAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: KotlinBookAdapter.ViewHolder, position: Int) {
        val current = content[position]
        Picasso.get().load(current.image).into(holder.image)
    }
    override fun getItemCount(): Int {
        return content.size
    }
}