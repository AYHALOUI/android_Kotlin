package com.training.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.leanback.widget.DiffCallback
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.training.myapplication.databinding.SportsListItemBinding
import com.training.myapplication.model.Sport
import okhttp3.internal.Util

class SportsAdapter(private val onItemClicked: (Sport) -> Unit) :
    ListAdapter<Sport, SportsAdapter.SportsViewHolder>(DiffCallback){

    private lateinit var context : Context

    class SportsViewHolder(private var binding: SportsListItemBinding):
        RecyclerView.ViewHolder(binding.root){

            fun bind(sport: Sport, context: Context){
                binding.title.text = context.getString(sport.titleResourceId)
                binding.subTitle.text = context.getString(sport.subTitleResourceId)
                binding.sportsImage.load(sport.imageResourceId)
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportsViewHolder {
        context = parent.context
        return SportsViewHolder(
            SportsListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: SportsViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClicked(current)
        }
        holder.bind(current, context)
    }

    companion object{
        private val DiffCallback = object : DiffUtil.ItemCallback<Sport>(){
            override fun areItemsTheSame(oldItem: Sport, newItem: Sport): Boolean {
                return (oldItem.id == newItem.id ||
                        oldItem.titleResourceId == newItem.titleResourceId ||
                        oldItem.subTitleResourceId == newItem.subTitleResourceId
                        )
            }

            override fun areContentsTheSame(oldItem: Sport, newItem: Sport): Boolean {
                return oldItem == newItem
            }

        }
    }
}