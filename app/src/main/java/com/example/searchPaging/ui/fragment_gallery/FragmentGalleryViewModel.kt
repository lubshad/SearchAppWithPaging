package com.example.searchPaging.ui.fragment_gallery

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.searchPaging.data.UnsplashRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class FragmentGalleryViewModel @Inject constructor(
    unsplashRepository: UnsplashRepository,
) : ViewModel() {


    private val searchQuery = MutableLiveData("")

    val photos = searchQuery.switchMap { query ->
        unsplashRepository.getSearchResult(query).cachedIn(viewModelScope)
    }

    fun searchPhotos(query: String) {
        searchQuery.value = query
    }

}