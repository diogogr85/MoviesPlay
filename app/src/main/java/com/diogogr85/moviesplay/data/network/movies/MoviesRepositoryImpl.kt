package com.diogogr85.moviesplay.data.network.movies

import com.diogogr85.moviesplay.domain.entity.Movie
import com.diogogr85.moviesplay.domain.repository.MoviesRepository
import com.diogogr85.moviesplay.utils.QUERY_PARAM_LANGUAGE
import com.diogogr85.moviesplay.utils.QUERY_PARAM_PAGE
import com.diogogr85.moviesplay.utils.QUERY_VALUE_LANGUAGE_BR
import com.diogogr85.moviesplay.utils.Utils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MoviesRepositoryImpl(private val moviesService: MoviesService): MoviesRepository {
    override fun getPopularMovies(pagination: Int): Flow<List<Movie>> = flow {
        val result = moviesService.getPopularMovies(
            Utils.queryParams(
                Pair(QUERY_PARAM_LANGUAGE, QUERY_VALUE_LANGUAGE_BR),
                Pair(QUERY_PARAM_PAGE, "$pagination")
            )
        )
        val moviesList = result.results

        emit(moviesList)
    }

    override fun getUpcomingMovies(pagination: Int): Flow<List<Movie>> = flow {
        val result = moviesService.getUpcomingMovies(
            Utils.queryParams(
                Pair(QUERY_PARAM_LANGUAGE, QUERY_VALUE_LANGUAGE_BR),
                Pair(QUERY_PARAM_PAGE, "$pagination")
            )
        )
        val moviesList = result.results

        emit(moviesList)
    }
}