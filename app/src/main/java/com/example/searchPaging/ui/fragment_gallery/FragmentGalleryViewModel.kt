package com.example.searchPaging.ui.fragment_gallery

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.searchPaging.api.UnsplashApi
import com.example.searchPaging.data.UnsplashRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FragmentGalleryViewModel @Inject constructor(
    unsplashRepository: UnsplashRepository,
    private val unsplashApi: UnsplashApi,
) : ViewModel() {


    private val searchQuery = MutableLiveData("cat")

    val photos = searchQuery.switchMap { query ->
        unsplashRepository.getSearchResult(query).cachedIn(viewModelScope)
    }

    fun searchPhotos(query: String) {
        searchQuery.value = query
    }

    fun search(query: String) {
        viewModelScope.launch {
            val response = unsplashApi.searchPhotos(query, 1, 20)
            Log.i("Search", response.toString())
            for (photo in response.hits) {
                Log.i("Search", photo.webformatURL)
            }
        }
    }


}