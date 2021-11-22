package com.example.searchPaging.ui.fragment_gallery

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.searchPaging.R
import com.example.searchPaging.databinding.FragmentGalleryBinding
import com.example.searchPaging.utils.onSubmitText
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FragmentGallery : Fragment(R.layout.fragment_gallery) {

    private val viewModel by viewModels<FragmentGalleryViewModel>()

    lateinit var unsplashPhotoAdapter: UnsplashPhotoAdapter

    lateinit var binding: FragmentGalleryBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentGalleryBinding.bind(view)
        unsplashPhotoAdapter = UnsplashPhotoAdapter()


        binding.apply {
            imageList.setHasFixedSize(true)
            imageList.adapter = unsplashPhotoAdapter.withLoadStateHeaderAndFooter(
                header = PhotoLoadStateAdapter { unsplashPhotoAdapter.retry() },
                footer = PhotoLoadStateAdapter { unsplashPhotoAdapter.retry() },
            )
        }

        viewModel.photos.observe(viewLifecycleOwner) {
            unsplashPhotoAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        setHasOptionsMenu(true)


    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.gallery_menu, menu)


        val actionSearch = menu.findItem(R.id.action_search)
        val searchView = actionSearch.actionView as SearchView


        searchView.onSubmitText {
            viewModel.searchPhotos(it)
            searchView.clearFocus()
            binding.imageList.scrollToPosition(0)
        }
    }
}