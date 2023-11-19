package com.example.thebigscreen.data.remote.response

import com.example.thebigscreen.model.Genre
import com.google.gson.annotations.SerializedName

data class GenreResponse(
    @SerializedName("genres")
    val genres: List<Genre>
)