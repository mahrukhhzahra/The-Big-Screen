@file:Suppress("KDocUnresolvedReference")

package com.example.thebigscreen.data.remote

import com.example.thebigscreen.data.remote.response.FilmResponse
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.thebigscreen.BuildConfig
import com.ramcosta.composedestinations.BuildConfig

interface ApiService {

    /** **Movies** **/

    @GET("trending/movie/day")
    suspend fun getTrendingMovies(
        @Query("page") page: Int = 0,
        @Query("api_key") apiKey: String = BuildConfig,
        @Query("language") language: String = "en",
    ): FilmResponse

}