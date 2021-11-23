@file:Suppress("unused", "SpellCheckingInspection")

package com.example.searchapp.domain.pixabay_api

import PixabayPagingSource
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.searchapp.data.models.pixabay.Hit
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class PixabayRepository @Inject constructor(
    private val pixabayApi: PixabayApi
) {

    fun getSearchResultStream(query: String): LiveData<PagingData<Hit>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5,
                maxSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PixabayPagingSource(pixabayApi, query) }
        ).liveData
    }
}