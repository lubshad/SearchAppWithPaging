package com.example.searchPaging.data

import UnsplashPagingSource
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.searchPaging.api.UnsplashApi
import com.example.searchPaging.data.unsplash.Result
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UnsplashRepository @Inject constructor(
    private val unsplashApi: UnsplashApi,
) {
    fun getSearchResult(query: String): LiveData<PagingData<Result>> {
        return Pager(config = PagingConfig(
            pageSize = 20,
            maxSize = 100,
            enablePlaceholders = false,
        ),
            pagingSourceFactory = { UnsplashPagingSource(unsplashApi, query) }).liveData
    }

}