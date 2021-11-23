package com.example.searchapp.ui.fragment_gallery

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.searchapp.R
import com.example.searchapp.databinding.FragmentGalleryBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FragmentGallery : Fragment(R.layout.fragment_gallery) {
    private val viewModel by viewModels<FragmentGalleryViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val binding = FragmentGalleryBinding.bind(view)

        binding.apply {
            fab.setOnClickListener {
                viewModel.searchImage("Cats")
            }
        }

//        viewModel.searchImage("Cats")
    }
}


