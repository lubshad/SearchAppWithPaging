package com.example.searchPaging.ui.fragment_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.searchPaging.R
import com.example.searchPaging.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FragmentDetails : Fragment(R.layout.fragment_details) {


    private val args by navArgs<FragmentDetailsArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentDetailsBinding.bind(view)
        val photo = args.photo

        binding.apply {
            Glide.with(view)
                .load(photo?.webformatURL)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(detailsImage)
            imageDescription.text = photo.tags
            uploadedByUser.text = "Uploaded by ${photo.user}"
        }
    }

}