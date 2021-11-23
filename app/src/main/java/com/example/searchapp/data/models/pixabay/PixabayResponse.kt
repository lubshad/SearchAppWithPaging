package com.example.searchapp.data.models.pixabay

@Suppress("SpellCheckingInspection")
data class PixabayResponse(
    val hits: List<Hit>,
    val total: Int,
    val totalHits: Int
)