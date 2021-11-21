package com.example.searchPaging.ui.fragment_gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.searchPaging.data.unsplash.PixabayPhoto
import com.example.searchPaging.databinding.UpsplashPhotoItemBinding

class UnsplashPhotoAdapter :
    PagingDataAdapter<PixabayPhoto, UnsplashPhotoAdapter.UnsplashPhotoViewHolder>(PhotoComparator) {

    override fun onBindViewHolder(holder: UnsplashPhotoViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UnsplashPhotoViewHolder {
        val binding = UpsplashPhotoItemBinding.inflate(LayoutInflater.from(parent.context), parent , false)

        return UnsplashPhotoViewHolder(binding)
    }

    class UnsplashPhotoViewHolder(private val binding: UpsplashPhotoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PixabayPhoto) {
            binding.apply {
                Glide.with(itemView)
                    .load(item.webformatURL)
                    .into(imageView)
            }
        }
    }


    object PhotoComparator : DiffUtil.ItemCallback<PixabayPhoto>() {
        override fun areItemsTheSame(oldItem: PixabayPhoto, newItem: PixabayPhoto): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PixabayPhoto, newItem: PixabayPhoto): Boolean {
            return oldItem == newItem
        }
    }

}