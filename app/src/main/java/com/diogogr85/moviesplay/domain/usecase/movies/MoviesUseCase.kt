package com.diogogr85.moviesplay.domain.usecase.movies

import com.diogogr85.moviesplay.domain.entity.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesUseCase {
    fun getPopularMovies(): Flow<List<Movie>>
    fun getUpcomingMovies(): Flow<List<Movie>>
}