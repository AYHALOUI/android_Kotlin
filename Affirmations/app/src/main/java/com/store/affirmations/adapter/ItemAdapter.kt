package com.store.affirmations.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.store.affirmations.R
import com.store.affirmations.model.Affirmation

class ItemAdapter(
    private val context : Context,
    private val dataset : List<Affirmation>
    ) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val view : View) : RecyclerView.ViewHolder(view){
        val text : TextView = view.findViewById(R.id.item_id)
        val imag : ImageView = view.findViewById(R.id.item_imag)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(context)
            .inflate(R.layout.list_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.text.text = context.resources.getString(item.stringResourceId)
        holder.imag.setImageResource(item.stringImageId)
    }

    override fun getItemCount() = dataset.size

}