@file:Suppress("SpellCheckingInspection")

package com.example.searchapp.ui.fragment_gallery

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.searchapp.R
import com.example.searchapp.databinding.FragmentGalleryBinding
import com.example.searchapp.utils.onQuerySubmit
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FragmentGallery : Fragment(R.layout.fragment_gallery) {
    private val viewModel by viewModels<FragmentGalleryViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val binding = FragmentGalleryBinding.bind(view)
        val pixabayImageAdapter = PixabayImageAdapter()

        binding.apply {
            recycerViewGallery.adapter = pixabayImageAdapter
        }

        viewModel.photos.observe(viewLifecycleOwner) { pagingData ->
            pixabayImageAdapter.submitData(viewLifecycleOwner.lifecycle, pagingData)
        }

        setHasOptionsMenu(true)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_gallery_menu, menu)


        val actionSearch = menu.findItem(R.id.action_search)
        val searchView = actionSearch.actionView as androidx.appcompat.widget.SearchView


        searchView.onQuerySubmit {
            viewModel.searchQuery.value = it
            searchView.clearFocus()
        }
    }
}


