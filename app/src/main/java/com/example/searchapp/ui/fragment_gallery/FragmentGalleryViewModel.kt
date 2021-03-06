@file:Suppress("SpellCheckingInspection")

package com.example.searchapp.ui.fragment_gallery

import androidx.lifecycle.*
import com.example.searchapp.domain.pixabay_api.PixabayRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class FragmentGalleryViewModel @Inject constructor(
    private val pixabayRepository: PixabayRepository,
    state: SavedStateHandle
) : ViewModel() {

    companion object {
        const val DEFAULT_SEARCH_QUERY = "Cats"
        const val CURRENT_SEARCH_QUERY = "current_search_query"
    }


    val searchQuery = state.getLiveData(CURRENT_SEARCH_QUERY, DEFAULT_SEARCH_QUERY)


    val photos = searchQuery.switchMap { searchQuery ->
        pixabayRepository.getSearchResultStream(searchQuery)
    }


}