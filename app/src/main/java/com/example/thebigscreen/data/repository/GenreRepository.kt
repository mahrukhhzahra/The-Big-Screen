package com.example.thebigscreen.data.repository

import com.example.thebigscreen.data.remote.ApiService
import com.example.thebigscreen.data.remote.response.GenreResponse
import com.example.thebigscreen.util.FilmType
import com.example.thebigscreen.util.Resource
import java.lang.Exception
import javax.inject.Inject

class GenreRepository @Inject constructor(private val api: ApiService) {
    suspend fun getMoviesGenre(filmType: FilmType): Resource<GenreResponse>{
        val response = try {
            if (filmType == FilmType.MOVIE) api.getMovieGenres() else api.getTvShowGenres()
        } catch (e: Exception){
            return Resource.Error("Unknown error occurred!")
        }
        return Resource.Success(response)
    }
}