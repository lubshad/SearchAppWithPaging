package com.example.searchPaging.api


import com.example.searchPaging.BuildConfig
import com.example.searchPaging.data.unsplash.PixabayApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApi {

    companion object {
        private const val CLIENT_ID = BuildConfig.CLIENT_ID
        const val BASE_URL = "https://pixabay.com/"
    }


    @GET("api/?key=$CLIENT_ID")
    suspend fun searchPhotos(
        @Query("q") searchKey: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ): PixabayApiResponse
}