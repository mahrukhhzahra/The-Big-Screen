package com.example.thebigscreen.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thebigscreen.data.local.MyListMovie
import com.example.thebigscreen.data.repository.WatchListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WatchListViewModel @Inject constructor(private val watchListRepository: WatchListRepository) :
    ViewModel() {

    private val _addedToWatchList = mutableStateOf(0)
    val addedToWatchList: MutableState<Int> = _addedToWatchList

    private val _watchList = mutableStateOf<Flow<List<MyListMovie>>>(emptyFlow())
    val watchList: MutableState<Flow<List<MyListMovie>>> = _watchList


    init {
        getWatchList()
    }


    fun addToWatchList(movie: MyListMovie) {
        viewModelScope.launch {
            watchListRepository.addToWatchList(movie = movie)
        }.invokeOnCompletion {
            exists(movie.mediaId)
        }
    }


    fun exists(mediaId: Int) {
        viewModelScope.launch {
            _addedToWatchList.value = watchListRepository.exists(mediaId)
        }
    }


    fun removeFromWatchList(mediaId: Int) {
        viewModelScope.launch {
            watchListRepository.removeFromWatchList(mediaId = mediaId)
        }.invokeOnCompletion {
            exists(mediaId = mediaId)
        }
    }

    private fun getWatchList() {
        _watchList.value = watchListRepository.getFullWatchList()
    }

    fun deleteWatchList() {
        viewModelScope.launch {
            watchListRepository.deleteWatchList()
        }
    }
}