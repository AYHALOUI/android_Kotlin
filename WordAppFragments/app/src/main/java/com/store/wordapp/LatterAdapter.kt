package com.store.wordapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class LatterAdapter (
    private val context : Context,
    ) : RecyclerView.Adapter<LatterAdapter.ItemViewHolder>()
{
     val latters = ('A').rangeTo('Z').toList()

    class ItemViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val btn : Button = view.findViewById(R.id.btn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater
            .from(context)
            .inflate(R.layout.list_item,parent,false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.btn.text = latters.get(position).toString()
        holder.btn.setOnClickListener {
            val action = LetterListFragmentDirections.actionLetterListFragmentToWordListFragment2(holder.btn.text.toString())
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount() = latters.size

}