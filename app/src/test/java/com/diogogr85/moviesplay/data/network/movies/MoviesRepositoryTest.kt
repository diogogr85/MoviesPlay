package com.diogogr85.moviesplay.data.network.movies

import com.diogogr85.moviesplay.data.models.MoviesApiResult
import com.diogogr85.moviesplay.domain.repository.MoviesRepository
import com.diogogr85.moviesplay.moviesApiResult
import com.diogogr85.moviesplay.utils.Utils
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.Test

class MoviesRepositoryTest {

    private val apiService = mockk<MoviesService>(relaxed = true)
    private val repository: MoviesRepository = MoviesRepositoryImpl(apiService)

    @Test
    fun `should get popular movies from api`() = runBlocking {
        coEvery { apiService.getPopularMovies(any()) } returns moviesApiResult

        val result = repository.getPopularMovies(QUERY_VALUE_PAGE).single()

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
    fun `should get upcoming movies from api`() = runBlocking {
        coEvery { apiService.getUpcomingMovies(any()) } returns moviesApiResult

        val result = repository.getUpcomingMovies(QUERY_VALUE_PAGE).single()

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

    private companion object {
        const val QUERY_PARAM_LANGUAGE = "language"
        const val QUERY_PARAM_PAGE = "page"
        const val QUERY_VALUE_LANGUAGE_BR = "pt-BR"
        const val QUERY_VALUE_PAGE = 1
    }
}