package com.example.thebigscreen.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.thebigscreen.data.remote.response.Review
import com.example.thebigscreen.data.repository.ReviewsRepository
import com.example.thebigscreen.util.FilmType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewsViewModel @Inject constructor(private val repo: ReviewsRepository) : ViewModel() {

    private val _filmReviews = mutableStateOf<Flow<PagingData<Review>>>(emptyFlow())
    val filmReviews: State<Flow<PagingData<Review>>> = _filmReviews

    fun getFilmReview(filmId: Int, filmType: FilmType) {
        viewModelScope.launch {
            _filmReviews.value = repo.getFilmReviews(filmId = filmId, filmType).cachedIn(viewModelScope)
        }
    }
}
