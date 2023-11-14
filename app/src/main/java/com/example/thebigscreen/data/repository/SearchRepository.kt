package com.example.thebigscreen.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.thebigscreen.data.remote.ApiService
import com.example.thebigscreen.model.Search
import com.example.thebigscreen.paging.SearchFilmSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRepository @Inject constructor(private val api: ApiService) {

    fun multiSearch(searchParams: String, includeAdult: Boolean): Flow<PagingData<Search>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                SearchFilmSource(
                    api = api,
                    searchParams = searchParams,
                    includeAdult = includeAdult
                )
            }
        ).flow
    }
}