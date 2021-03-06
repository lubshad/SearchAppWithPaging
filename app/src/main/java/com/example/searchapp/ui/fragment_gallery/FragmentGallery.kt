@file:Suppress("SpellCheckingInspection")

package com.example.searchapp.ui.fragment_gallery

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import com.example.searchapp.R
import com.example.searchapp.databinding.FragmentGalleryBinding
import com.example.searchapp.utils.onQuerySubmit
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FragmentGallery : Fragment(R.layout.fragment_gallery) {
    private val viewModel by viewModels<FragmentGalleryViewModel>()


    private lateinit var binding: FragmentGalleryBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding = FragmentGalleryBinding.bind(view)
        val pixabayImageAdapter = PixabayImageAdapter()

        binding.apply {


            recyclerViewGallery.adapter = pixabayImageAdapter.withLoadStateHeaderAndFooter(
                header = PixabayLoadStateAdapter { pixabayImageAdapter.retry() },
                footer = PixabayLoadStateAdapter { pixabayImageAdapter.retry() }
            )
            recyclerViewGallery.itemAnimator = null

            retryButton.setOnClickListener {
                pixabayImageAdapter.retry()
            }
        }

        viewModel.photos.observe(viewLifecycleOwner) { pagingData ->
            pixabayImageAdapter.submitData(viewLifecycleOwner.lifecycle, pagingData)
        }

        pixabayImageAdapter.addLoadStateListener { loadState ->

            binding.apply {
                galleryLoading.isVisible = loadState.source.refresh is LoadState.Loading
                retryButton.isVisible = loadState.source.refresh is LoadState.Error
                textViewError.isVisible = loadState.source.refresh is LoadState.Error
                recyclerViewGallery.isVisible =
                    loadState.source.refresh !is LoadState.Error && loadState.source.refresh !is LoadState.Loading && pixabayImageAdapter.itemCount > 0



                if (loadState.refresh is LoadState.NotLoading &&
                    pixabayImageAdapter.itemCount < 1
                ) {
                    recyclerViewGallery.isVisible = false
                    textViewNoItem.isVisible = true
                } else {
                    textViewNoItem.isVisible = false
                }
            }

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
            binding
                .recyclerViewGallery.scrollToPosition(0)
        }
    }
}


