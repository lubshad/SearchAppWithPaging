package com.example.searchapp.domain.pixabay_api

import com.example.searchapp.BuildConfig
import com.example.searchapp.data.models.pixabay.PixabayResponse
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Suppress("SpellCheckingInspection")
@Singleton
interface PixabayApi {

    companion object {
        const val BASE_URL = "https://pixabay.com/"
        const val PIXABAY_KEY = BuildConfig.PIXABAY_KEY
    }


    @GET("api/?key=$PIXABAY_KEY")
    suspend fun searchPhoto(
        @Query("q") query: String,
        @Query("page")page: Int,
        @Query("per_page") perPage: Int
    ): PixabayResponse
}