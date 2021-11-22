package com.example.searchPaging.ui.fragment_gallery

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.searchPaging.R
import com.example.searchPaging.databinding.FragmentGalleryBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FragmentGallery : Fragment(R.layout.fragment_gallery) {

    private val viewModel by viewModels<FragmentGalleryViewModel>()

    lateinit var unsplashPhotoAdapter: UnsplashPhotoAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentGalleryBinding.bind(view)
        unsplashPhotoAdapter = UnsplashPhotoAdapter()


        binding.apply {
            imageList.setHasFixedSize(true)
            imageList.adapter = unsplashPhotoAdapter.withLoadStateHeaderAndFooter(
                header = PhotoLoadStateAdapter{unsplashPhotoAdapter.retry()},
                footer = PhotoLoadStateAdapter{unsplashPhotoAdapter.retry()},
            )
        }

        viewModel.photos.observe(viewLifecycleOwner) {
            unsplashPhotoAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }


    }
}