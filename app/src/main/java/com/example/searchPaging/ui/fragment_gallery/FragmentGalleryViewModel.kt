package com.example.searchPaging.ui.fragment_gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.searchPaging.api.UnsplashApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FragmentGalleryViewModel @Inject constructor(
    private val unsplashApi: UnsplashApi,
) : ViewModel() {

    fun searchImage() {
        viewModelScope.launch {
            unsplashApi.searchPhotos(searchKey = "image", page = 1, perPage = 5)
        }
    }

}