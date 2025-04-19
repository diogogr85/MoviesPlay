package com.diogogr85.moviesplay.data.network.movies

import com.diogogr85.moviesplay.domain.entity.Movie
import com.diogogr85.moviesplay.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MoviesRepositoryImpl(private val moviesService: MoviesService): MoviesRepository {
    override fun getPopularMovies(): Flow<List<Movie>> = flow {
        val result = moviesService.getPopularMovies()
        val moviesList = result.results

        emit(moviesList)
    }
}