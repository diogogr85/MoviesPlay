package com.diogogr85.moviesplay.domain.usecase.movies

import com.diogogr85.moviesplay.data.models.Movie
import com.diogogr85.moviesplay.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow

interface MoviesUseCase {
    fun getMovies(): Flow<List<Movie>>
}