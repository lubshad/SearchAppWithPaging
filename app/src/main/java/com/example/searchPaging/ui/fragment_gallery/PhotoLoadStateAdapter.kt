package com.example.searchPaging.ui.fragment_gallery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.searchPaging.databinding.PhotoLoadStateBinding

class PhotoLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<PhotoLoadStateAdapter.PhotoLoadStateViewHolder>() {

    inner class PhotoLoadStateViewHolder(private val binding: PhotoLoadStateBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.apply {
                retryButton.setOnClickListener {
                    retry.invoke()
                }
            }
        }


        fun bind(loadState: LoadState) {
            binding.apply {
                progressBar.isVisible = loadState is LoadState.Loading
                errorLoadingText.isVisible = loadState !is LoadState.Loading
                retryButton.isVisible = loadState !is LoadState.Loading
            }
        }

    }

    override fun onBindViewHolder(holder: PhotoLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): PhotoLoadStateViewHolder {
        val binding = PhotoLoadStateBinding.inflate(LayoutInflater.from(parent.context) , parent, false)
        return PhotoLoadStateViewHolder(binding)
    }
}