package com.example.searchPaging.data.unsplash

data class PixabayApiResponse(
    val hits: List<PixabayPhoto>,
    val total: Int,
    val totalHits: Int
)