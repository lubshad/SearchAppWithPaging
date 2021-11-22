package com.example.searchPaging.ui.fragment_gallery

import androidx.lifecycle.SavedStateHandle
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
    state: SavedStateHandle,
) : ViewModel() {

    companion object {
        const val SEARCH_QUERY = "search_query"
        const val DEFAULT_QUERY = "cats"
    }


    private val searchQuery = state.getLiveData(SEARCH_QUERY, DEFAULT_QUERY)

    val photos = searchQuery.switchMap { query ->
        unsplashRepository.getSearchResult(query).cachedIn(viewModelScope)
    }

    fun searchPhotos(query: String) {
        searchQuery.value = query
    }


}