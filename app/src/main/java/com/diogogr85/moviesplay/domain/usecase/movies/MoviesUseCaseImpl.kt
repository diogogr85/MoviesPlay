package com.diogogr85.moviesplay.domain.usecase.movies

import com.diogogr85.moviesplay.domain.entity.Movie
import com.diogogr85.moviesplay.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow

class MoviesUseCaseImpl(private val moviesRepository: MoviesRepository): MoviesUseCase {
    override fun getPopularMovies(): Flow<List<Movie>> = moviesRepository.getPopularMovies()

    override fun getUpcomingMovies(): Flow<List<Movie>> = moviesRepository.getUpcomingMovies()
}