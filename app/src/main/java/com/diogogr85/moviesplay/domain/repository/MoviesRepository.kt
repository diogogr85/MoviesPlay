package com.diogogr85.moviesplay.domain.repository

import com.diogogr85.moviesplay.domain.entity.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun getPopularMovies(): Flow<List<Movie>>
    fun getUpcomingMovies(): Flow<List<Movie>>
}