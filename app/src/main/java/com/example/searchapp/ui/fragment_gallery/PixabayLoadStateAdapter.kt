@file:Suppress("SpellCheckingInspection")

package com.example.searchapp.ui.fragment_gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.searchapp.databinding.LoadStateBinding
import com.example.searchapp.ui.fragment_gallery.PixabayLoadStateAdapter.PixabayLoadStateViewHolder

class PixabayLoadStateAdapter(private val retry: ()->Unit) : LoadStateAdapter<PixabayLoadStateViewHolder>() {
    override fun onBindViewHolder(holder: PixabayLoadStateViewHolder, loadState: LoadState) {

        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState,
    ): PixabayLoadStateViewHolder {
        val binding = LoadStateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PixabayLoadStateViewHolder(binding)
    }

    inner class PixabayLoadStateViewHolder(private val binding: LoadStateBinding) :
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
                progressCircularLoadingMore.isVisible = loadState is LoadState.Loading
                textViewError.isVisible = loadState !is LoadState.Loading
                retryButton.isVisible = loadState !is LoadState.Loading
            }
        }

    }
}