package com.diogogr85.moviesplay.data.models

import com.diogogr85.moviesplay.domain.entity.Movie
import com.google.gson.annotations.SerializedName

data class MoviesApiResult(
    val page: Int,
    val results: List<Movie>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)