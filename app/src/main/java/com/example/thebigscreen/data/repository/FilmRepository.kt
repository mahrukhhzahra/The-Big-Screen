package com.example.thebigscreen.data.repository

import android.content.res.Resources
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.thebigscreen.data.remote.ApiService
import com.example.thebigscreen.data.remote.response.CastResponse
import com.example.thebigscreen.model.Film
import com.example.thebigscreen.paging.BackInTheDaysFilmSource
import com.example.thebigscreen.paging.PopularFilmSource
import com.example.thebigscreen.paging.RecommendedFilmSource
import com.example.thebigscreen.paging.SimilarFilmSource
import com.example.thebigscreen.paging.TopRatedFilmSource
import com.example.thebigscreen.paging.TrendingFilmSource
import com.example.thebigscreen.paging.UpcomingFilmSource
import com.example.thebigscreen.util.FilmType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FilmRepository @Inject constructor(private val api: ApiService) {

    fun getTrendingFilms(filmType: FilmType): Flow<PagingData<Film>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                TrendingFilmSource(api = api, filmType = filmType)
            }
        ).flow
    }

    fun getPopularMovies(filmType: FilmType): Flow<PagingData<Film>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                PopularFilmSource(api = api, filmType = filmType)
            }
        ).flow
    }

    fun getTopRatedFilms(filmType: FilmType): Flow<PagingData<Film>> {
        return Pager(
         config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                TopRatedFilmSource(api = api, filmType = filmType)
            }
        ).flow
    }

    fun getUpcomingTvShows(): Flow<PagingData<Film>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                UpcomingFilmSource(api = api)
            }
        ).flow
    }


    fun getBackInTheDaysFilms(filmType: FilmType): Flow<PagingData<Film>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                BackInTheDaysFilmSource(api = api, filmType = filmType)
            }
        ).flow
    }


    fun getSimilarFilms(movieId: Int, filmType: FilmType): Flow<PagingData<Film>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                SimilarFilmSource(api = api, filmId = movieId, filmType = filmType)
            }
        ).flow
    }


    fun getRecommendedFilms(movieId: Int, filmType: FilmType): Flow<PagingData<Film>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                RecommendedFilmSource(api = api, filmId = movieId, filmType= filmType)
            }
        ).flow
    }






    /** Non-paging data **/

    suspend fun getFilmCast(filmId: Int, filmType: FilmType): Resources<CastResponse> {
        val response = try {

        } catch (e: Exception) {
            return Resource.
        }
    }

}


