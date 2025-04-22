package com.diogogr85.moviesplay.data.network.movies

import com.diogogr85.moviesplay.data.helper.TimeHelper
import com.diogogr85.moviesplay.data.local.MoviePLayPrefs
import com.diogogr85.moviesplay.data.local.dao.MoviesDao
import com.diogogr85.moviesplay.data.local.entity.MovieType
import com.diogogr85.moviesplay.data.local.entity.MovieType.POPULAR
import com.diogogr85.moviesplay.data.local.entity.MovieType.UPCOMING
import com.diogogr85.moviesplay.data.models.toMovies
import com.diogogr85.moviesplay.data.models.toPopularEntityDB
import com.diogogr85.moviesplay.data.models.toUpcomingEntityDB
import com.diogogr85.moviesplay.domain.entity.Movie
import com.diogogr85.moviesplay.domain.repository.MoviesRepository
import com.diogogr85.moviesplay.utils.QUERY_PARAM_LANGUAGE
import com.diogogr85.moviesplay.utils.QUERY_PARAM_PAGE
import com.diogogr85.moviesplay.utils.QUERY_VALUE_LANGUAGE_BR
import com.diogogr85.moviesplay.utils.Utils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MoviesRepositoryImpl(
    private val moviesService: MoviesService,
    private val moviesDao: MoviesDao,
    private val prefs: MoviePLayPrefs,
    private val timeHelper: TimeHelper = TimeHelper()
): MoviesRepository {
    override fun getPopularMovies(pagination: Int): Flow<List<Movie>> = flow {
        val moviesList = if(isDbExpired()) {
            moviesDao.deleteByType(POPULAR.name)
            getMoviesFromApi(pagination, POPULAR)
        } else {
            tryMoviesFromDatabase(pagination, POPULAR)
        }

        emit(moviesList)
    }

    override fun getUpcomingMovies(pagination: Int): Flow<List<Movie>> = flow {
        val moviesList = if(isDbExpired()) {
            moviesDao.deleteByType(UPCOMING.name)
            getMoviesFromApi(pagination, UPCOMING)
        } else {
            tryMoviesFromDatabase(pagination, UPCOMING)
        }

        emit(moviesList)
    }

    private suspend fun tryMoviesFromDatabase(pagination: Int, movieType: MovieType): List<Movie> {
        var movieList = moviesDao.getMoviesByType(movieType.name).toMovies()

        if (movieList.isEmpty()) {
            movieList = getMoviesFromApi(pagination, movieType)
        }

        return movieList
    }

    private suspend fun getMoviesFromApi(pagination: Int, movieType: MovieType): List<Movie> {
        val queryParams = Utils.queryParams(
            Pair(QUERY_PARAM_LANGUAGE, QUERY_VALUE_LANGUAGE_BR),
            Pair(QUERY_PARAM_PAGE, "$pagination")
        )

        val result = when(movieType) {
            POPULAR -> moviesService.getPopularMovies(queryParams).apply {
                moviesDao.insertAll(results.toPopularEntityDB())
            }
            UPCOMING -> moviesService.getUpcomingMovies(queryParams).apply {
                moviesDao.insertAll(results.toUpcomingEntityDB())
            }
        }
        val movieList = result.results
        prefs.dbTimestamp = timeHelper.getCurrentTimestamp()

        return movieList
    }

    private fun isDbExpired() = (timeHelper.getCurrentTimestamp() - prefs.dbTimestamp) > DB_EXPIRATION_TIME

    private companion object {
        const val DB_EXPIRATION_TIME = 30000L
    }
}