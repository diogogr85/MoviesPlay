package com.diogogr85.moviesplay.data.network.movies

import com.diogogr85.moviesplay.data.helper.TimeHelper
import com.diogogr85.moviesplay.data.local.MoviePLayPrefs
import com.diogogr85.moviesplay.data.local.dao.MoviesDao
import com.diogogr85.moviesplay.data.local.entity.MovieType.POPULAR
import com.diogogr85.moviesplay.data.local.entity.MovieType.UPCOMING
import com.diogogr85.moviesplay.data.models.toPopularEntityDB
import com.diogogr85.moviesplay.data.models.toUpcomingEntityDB
import com.diogogr85.moviesplay.domain.repository.MoviesRepository
import com.diogogr85.moviesplay.moviesApiResult
import com.diogogr85.moviesplay.utils.Utils
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.Test

class MoviesRepositoryTest {

    private val apiService = mockk<MoviesService>(relaxed = true)
    private val moviesDao = mockk<MoviesDao>(relaxed = true)
    private val prefs = mockk<MoviePLayPrefs>(relaxed = true)
    private val timeHelper = mockk<TimeHelper>(relaxed = true)
    private val repository: MoviesRepository = MoviesRepositoryImpl(apiService, moviesDao, prefs, timeHelper)

    @Test
    fun `should get popular movies from api when cache is expired`() = runBlocking {
        every { prefs.dbTimestamp } returns 10000
        every { timeHelper.getCurrentTimestamp() } returns 110000
        coEvery { apiService.getPopularMovies(any()) } returns moviesApiResult

        val result = repository.getPopularMovies(QUERY_VALUE_PAGE).single()

        coVerify(exactly = 0) { moviesDao.getMoviesByType(POPULAR.name) }
        coVerify {
            apiService
                .getPopularMovies(
                    Utils.queryParams(
                        Pair(QUERY_PARAM_LANGUAGE, QUERY_VALUE_LANGUAGE_BR),
                        Pair(QUERY_PARAM_PAGE, "$QUERY_VALUE_PAGE")
                    )
                )
        }
        assert(result == moviesApiResult.results)
    }

    @Test
    fun `should get popular movies from database when cache is not expired`() = runBlocking {
        every { prefs.dbTimestamp } returns 100000
        every { timeHelper.getCurrentTimestamp() } returns 110000
        coEvery { moviesDao.getMoviesByType(POPULAR.name) } returns moviesApiResult.results.toPopularEntityDB()

        val result = repository.getPopularMovies(QUERY_VALUE_PAGE).single()

        coVerify(exactly = 1) { moviesDao.getMoviesByType(POPULAR.name) }
        coVerify(exactly = 0) { apiService.getPopularMovies(any()) }
        assert(result == moviesApiResult.results)
    }

    @Test
    fun `should get upcoming movies from api when cache is expired`() = runBlocking {
        every { prefs.dbTimestamp } returns 10000
        every { timeHelper.getCurrentTimestamp() } returns 110000
        coEvery { apiService.getUpcomingMovies(any()) } returns moviesApiResult

        val result = repository.getUpcomingMovies(QUERY_VALUE_PAGE).single()

        coVerify(exactly = 0) { moviesDao.getMoviesByType(UPCOMING.name) }
        coVerify {
            apiService
                .getUpcomingMovies(
                    Utils.queryParams(
                        Pair(QUERY_PARAM_LANGUAGE, QUERY_VALUE_LANGUAGE_BR),
                        Pair(QUERY_PARAM_PAGE, "$QUERY_VALUE_PAGE")
                    )
                )
        }
        assert(result == moviesApiResult.results)
    }

    @Test
    fun `should get upcoming movies from database when cache is not expired`() = runBlocking {
        every { prefs.dbTimestamp } returns 100000
        every { timeHelper.getCurrentTimestamp() } returns 110000
        coEvery { moviesDao.getMoviesByType(UPCOMING.name) } returns moviesApiResult.results.toUpcomingEntityDB()

        val result = repository.getUpcomingMovies(QUERY_VALUE_PAGE).single()

        coVerify(exactly = 1) { moviesDao.getMoviesByType(UPCOMING.name) }
        coVerify(exactly = 0) { apiService.getUpcomingMovies(any()) }
        assert(result == moviesApiResult.results)
    }

    private companion object {
        const val QUERY_PARAM_LANGUAGE = "language"
        const val QUERY_PARAM_PAGE = "page"
        const val QUERY_VALUE_LANGUAGE_BR = "pt-BR"
        const val QUERY_VALUE_PAGE = 1
    }
}