package com.diogogr85.moviesplay.domain.repository

import com.diogogr85.moviesplay.data.models.Movie
import com.diogogr85.moviesplay.data.network.movies.MoviesService
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun getMovies(): Flow<List<Movie>>
}