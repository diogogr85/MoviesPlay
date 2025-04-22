package com.diogogr85.moviesplay.data.models

import com.diogogr85.moviesplay.data.local.entity.MovieEntityDB
import com.diogogr85.moviesplay.data.local.entity.MovieType
import com.diogogr85.moviesplay.data.local.entity.MovieType.POPULAR
import com.diogogr85.moviesplay.data.local.entity.MovieType.UPCOMING
import com.diogogr85.moviesplay.domain.entity.Movie

fun Movie.toEntityDB(type: MovieType) =
    MovieEntityDB(
        id = id,
        title = title,
        originalTitle = originalTitle,
        description = description,
        posterPath = posterPath,
        voteAverage = voteAverage,
        releaseDate = releaseDate,
        type = type.name
    )

fun List<Movie>.toPopularEntityDB() = map { it.toEntityDB(POPULAR) }

fun List<Movie>.toUpcomingEntityDB() = map { it.toEntityDB(UPCOMING) }

fun MovieEntityDB.toMovie() =
    Movie(
        id = id,
        title = title,
        originalTitle = originalTitle,
        description = description,
        posterPath = posterPath,
        voteAverage = voteAverage,
        releaseDate = releaseDate
    )

fun List<MovieEntityDB>.toMovies() = map { it.toMovie() }
