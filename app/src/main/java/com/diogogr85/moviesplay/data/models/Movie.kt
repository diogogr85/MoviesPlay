package com.diogogr85.moviesplay.data.models

data class Movie(
    val id: String,
    val title: String,
    val description: String,
    val posterPath: String,
    val backdropPath: String,
    val voteAverage: Double,
    val releaseDate: String
    )