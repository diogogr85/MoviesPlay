package com.diogogr85.moviesplay.domain.usecase.movies

import com.diogogr85.moviesplay.domain.repository.MoviesRepository
import com.diogogr85.moviesplay.moviesApiResult
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class MoviesUseCaseTest {
    private val repository = mockk<MoviesRepository>(relaxed = true)
    private val moviesUseCase: MoviesUseCase = MoviesUseCaseImpl(repository)

    @Test
    fun `should call repository getPopularMovies with default page when useCase get popular is called`() {
        moviesUseCase.getPopularMovies()

        verify { repository.getPopularMovies(PAGE_DEFAULT) }
    }

    @Test
    fun `should call repository getPopularMovies in specific page when useCase get popular is called with param`() {
        moviesUseCase.getPopularMovies(PAGE_2)

        verify { repository.getPopularMovies(PAGE_2) }
    }

    @Test
    fun `should collect popular movies from repository`() = runBlocking {
        coEvery { repository.getPopularMovies(any()) } returns flow { emit(moviesApiResult.results) }

        moviesUseCase.getPopularMovies().collectLatest { list ->
            assert(list.isNotEmpty())
            assertEquals(moviesApiResult.results, list)
        }
    }

    @Test
    fun `should call repository getUpcomingMovies with default page when useCase get upcoming is called`() {
        moviesUseCase.getUpcomingMovies()

        verify { repository.getUpcomingMovies(PAGE_DEFAULT) }
    }

    @Test
    fun `should call repository getUpcomingMovies in specific page when useCase get upcoming is called with param`() {
        moviesUseCase.getUpcomingMovies(PAGE_2)

        verify { repository.getUpcomingMovies(PAGE_2) }
    }

    @Test
    fun `should collect upcoming movies from repository`() = runBlocking {
        coEvery { repository.getUpcomingMovies(any()) } returns flow { emit(moviesApiResult.results) }

        moviesUseCase.getUpcomingMovies().collectLatest { list ->
            assert(list.isNotEmpty())
            assertEquals(moviesApiResult.results, list)
        }
    }

    private companion object {
        const val PAGE_DEFAULT = 1
        const val PAGE_2 = 2
    }
}