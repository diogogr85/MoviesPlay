package com.diogogr85.moviesplay

import com.diogogr85.moviesplay.data.models.MoviesApiResult
import com.diogogr85.moviesplay.domain.entity.Movie

private const val PAGE_DEFAULT = 1
private const val TOTAL_PAGE_DEFAULT = 20
private const val TOTAL_RESULTS_DEFAULT = 1000

val moviesApiResult = MoviesApiResult(
    PAGE_DEFAULT,
    listOf(
        Movie(
            id = 1,
            title = "title1",
            originalTitle = "original title1",
            description = "description1",
            posterPath = "poster.path/123.jpg",
            voteAverage = 7.5,
            releaseDate = "2025-01-01"
        ),
        Movie(
            id = 2,
            title = "title2",
            originalTitle = "original title2",
            description = "description2",
            posterPath = "poster.path/1234.jpg",
            voteAverage = 5.0,
            releaseDate = "2025-01-01"
        )
    ),
    TOTAL_PAGE_DEFAULT,
    TOTAL_RESULTS_DEFAULT
)

