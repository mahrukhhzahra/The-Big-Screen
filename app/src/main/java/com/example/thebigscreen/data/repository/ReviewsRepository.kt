package com.example.thebigscreen.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.thebigscreen.data.remote.ApiService
import com.example.thebigscreen.data.remote.response.Review
import com.example.thebigscreen.paging.ReviewsSource
import com.example.thebigscreen.util.FilmType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReviewsRepository @Inject constructor(private val api: ApiService) {

    fun getFilmReviews(filmId: Int, filmType: FilmType): Flow<PagingData<Review>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                ReviewsSource(api = api, filmId = filmId, filmType = filmType)
            }
        ).flow
    }

}