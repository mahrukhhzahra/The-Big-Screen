package com.example.thebigscreen.data.repository

import com.example.thebigscreen.data.local.MyListMovie
import com.example.thebigscreen.data.local.WatchListDatabase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WatchListRepository @Inject constructor(private val database: WatchListDatabase) {

    suspend fun addToWatchList(movie: MyListMovie) {
        database.movieDao.addToWatchList(movie = movie)
    }

    suspend fun exists(mediaId: Int): Int {
        return database.movieDao.exists(mediaId = mediaId)
    }

    suspend fun removeFromWatchList(mediaId: Int) {
        database.movieDao.removeFromWatchList(mediaId = mediaId)
    }

    suspend fun getFullWatchList(): Flow<List<MyListMovie>> {
        return database.movieDao.getFullWatchList()
    }

    suspend fun deleteWatchList() {
        database.movieDao.deleteWatchList()
    }

}