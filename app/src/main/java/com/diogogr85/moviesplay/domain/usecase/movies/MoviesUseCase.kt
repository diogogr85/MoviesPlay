package com.diogogr85.moviesplay.domain.usecase.movies

import com.diogogr85.moviesplay.domain.entity.Movie
import com.diogogr85.moviesplay.utils.PAGE_DEFAULT
import kotlinx.coroutines.flow.Flow

interface MoviesUseCase {
    fun getPopularMovies(pagination: Int = PAGE_DEFAULT): Flow<List<Movie>>
    fun getUpcomingMovies(pagination: Int = PAGE_DEFAULT): Flow<List<Movie>>
}