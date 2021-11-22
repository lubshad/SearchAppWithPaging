package com.example.searchPaging.ui.fragment_gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.searchPaging.data.unsplash.PixabayPhoto
import com.example.searchPaging.databinding.UpsplashPhotoItemBinding

class UnsplashPhotoAdapter(private val itemClickListener: OnItemClickListener) :
    PagingDataAdapter<PixabayPhoto, UnsplashPhotoAdapter.UnsplashPhotoViewHolder>(PhotoComparator) {

    override fun onBindViewHolder(holder: UnsplashPhotoViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UnsplashPhotoViewHolder {
        val binding =
            UpsplashPhotoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return UnsplashPhotoViewHolder(binding)
    }

    inner class UnsplashPhotoViewHolder(private val binding: UpsplashPhotoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                val photo = getItem(position)
                if (photo != null) {
                    itemClickListener.onClick(photo)
                }
            }
        }


        fun bind(item: PixabayPhoto) {
            binding.apply {
                userName.text = item.user
                Glide.with(itemView)
                    .load(item.webformatURL)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView)

            }
        }
    }


    interface OnItemClickListener {
        fun onClick(photo: PixabayPhoto)
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