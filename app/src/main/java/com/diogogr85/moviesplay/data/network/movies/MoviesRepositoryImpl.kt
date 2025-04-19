package com.diogogr85.moviesplay.data.network.movies

import com.diogogr85.moviesplay.data.models.Movie
import com.diogogr85.moviesplay.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MoviesRepositoryImpl(private val moviesService: MoviesService): MoviesRepository {
    override fun getMovies(): Flow<List<Movie>> = flow {
        val moviesList = moviesService.getMovies()

        emit(moviesList)
    }
}