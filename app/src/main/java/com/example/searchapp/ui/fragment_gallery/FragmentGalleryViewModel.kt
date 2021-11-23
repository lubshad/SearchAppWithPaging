package com.example.searchapp.ui.fragment_gallery

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.searchapp.domain.pixabay_api.PixabayApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class FragmentGalleryViewModel @Inject constructor(
    private val pixabayApi: PixabayApi
): ViewModel() {


    companion object {
        const val TAG = "Gallery"
    }


    fun searchImage(query: String) {
        viewModelScope.launch {
            val response = pixabayApi.searchPhoto(query, 1, 20)
            response
                .hits.forEach {
                    Log.i(TAG, it.user)
                }
        }
    }
}