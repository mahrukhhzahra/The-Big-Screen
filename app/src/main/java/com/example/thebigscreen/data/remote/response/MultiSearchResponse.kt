package com.example.thebigscreen.data.remote.response

import com.example.thebigscreen.model.Search
import com.google.gson.annotations.SerializedName

data class MultiSearchResponse (
    @SerializedName("pages")
    val page: Int,
    @SerializedName("results")
    val results: List<Search>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)