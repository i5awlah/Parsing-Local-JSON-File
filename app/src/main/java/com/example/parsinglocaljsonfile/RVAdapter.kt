package com.example.parsinglocaljsonfile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.parsinglocaljsonfile.databinding.ItemRowBinding

class RVAdapter(var images: ArrayList<Image>) : RecyclerView.Adapter<RVAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVAdapter.ViewHolder {
        return ViewHolder(
            ItemRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RVAdapter.ViewHolder, position: Int) {
        val image = images[position]

        holder.binding.apply {
            textView.text = image.title
            Glide.with(holder.itemView.context)
                .load(image.url)
                .into(imageView)
        }
    }

    override fun getItemCount(): Int = images.size

    fun update(images: ArrayList<Image>) {
        this.images = images
        notifyDataSetChanged()
    }

}