package com.diogogr85.moviesplay.domain.repository

import com.diogogr85.moviesplay.domain.entity.Movie
import com.diogogr85.moviesplay.utils.PAGE_DEFAULT
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun getPopularMovies(pagination: Int): Flow<List<Movie>>
    fun getUpcomingMovies(pagination: Int): Flow<List<Movie>>
}