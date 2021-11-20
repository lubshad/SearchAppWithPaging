package com.example.searchPaging.ui.fragment_gallery

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.searchPaging.R

class FragmentGallery: Fragment(R.layout.fragment_gallery) {

    val viewModel: FragmentGalleryViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.photos.observe(viewLifecycleOwner) {

        }
    }
}