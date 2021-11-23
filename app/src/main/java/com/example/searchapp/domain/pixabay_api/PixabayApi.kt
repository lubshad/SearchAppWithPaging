package com.example.searchapp.domain.pixabay_api

import com.example.searchapp.BuildConfig
import retrofit2.http.GET

interface PixabayApi {

    companion object  {
        const val BASE_URL = "https://pixabay.com/"
        const val PIXABAY_KEY = "BuildConfig.PIXABAY_KEY"
    }

    suspend fun searchPhoto(query: String) : PixabayApi
}