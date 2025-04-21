package com.diogogr85.moviesplay.domain.presentation.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.diogogr85.moviesplay.CoroutineTestRule
import com.diogogr85.moviesplay.domain.usecase.movies.MoviesUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class MoviesViewModelTest {

    @get:Rule
    val coroutineTestRule: CoroutineTestRule = CoroutineTestRule()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val moviesUseCase = mockk<MoviesUseCase>(relaxed = true)
    private lateinit var viewModel: MoviesViewModel

    @Test
    fun `should fetch popular and upcoming movies successfully when init viewmodel`() = runTest {
        every { moviesUseCase.getPopularMovies() } returns flowOf(emptyList())
        every { moviesUseCase.getUpcomingMovies() } returns flowOf(emptyList())

        viewModel = MoviesViewModel(moviesUseCase)

        verify { moviesUseCase.getPopularMovies() }
        verify { moviesUseCase.getUpcomingMovies() }
    }
}