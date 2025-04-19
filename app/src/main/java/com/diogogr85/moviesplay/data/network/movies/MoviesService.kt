package com.diogogr85.moviesplay.data.network.movies

import com.diogogr85.moviesplay.data.models.Movie

interface MoviesService {
    suspend fun getMovies(): List<Movie>
}