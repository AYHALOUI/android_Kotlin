package com.training.loadimagesfrominternet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.training.loadimagesfrominternet.databinding.GridViewItemBinding
import com.training.loadimagesfrominternet.network.MarsPhoto

class PhotoGridAdapter : ListAdapter<MarsPhoto, PhotoGridAdapter.MarsPhotoViewHolder>(DiffCalback) {

    class MarsPhotoViewHolder(private val binding: GridViewItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(MarsPhoto: MarsPhoto){
            binding.photo = MarsPhoto
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsPhotoViewHolder {
        return MarsPhotoViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MarsPhotoViewHolder, position: Int) {
        val marsPhoto = getItem(position)
        holder.bind(marsPhoto)
    }

    companion object DiffCalback : DiffUtil.ItemCallback<MarsPhoto>(){
        override fun areItemsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean {
            return oldItem.imgSrcUrl == newItem.imgSrcUrl
        }

    }
}