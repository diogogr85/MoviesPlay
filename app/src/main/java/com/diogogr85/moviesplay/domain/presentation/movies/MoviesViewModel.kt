package com.diogogr85.moviesplay.domain.presentation.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diogogr85.moviesplay.domain.entity.Movie
import com.diogogr85.moviesplay.domain.usecase.movies.MoviesUseCase
import com.diogogr85.moviesplay.ui.composable.ScreenErrorManager
import com.diogogr85.moviesplay.ui.composable.ScreenLoadingManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch

class MoviesViewModel(private val moviesUseCase: MoviesUseCase): ViewModel() {

    private var _popularMoviesListData = MutableLiveData<List<Movie>>()
    val popularMoviesListData: LiveData<List<Movie>> = _popularMoviesListData

    private var _upcomingMoviesListData = MutableLiveData<List<Movie>>()
    val upcomingMoviesListData: LiveData<List<Movie>> = _upcomingMoviesListData

    init {
        loadAllMovies()
    }

    fun loadAllMovies() {
        ScreenLoadingManager.showLoading()
        ScreenErrorManager.hideErrorScreen()
        loadPopularMovies()
        loadUpcomingMovies()
    }

    private fun loadPopularMovies() {
        viewModelScope.launch {
            try {
                val moviesList = moviesUseCase
                    .getPopularMovies()
                    .flowOn(Dispatchers.IO)
                    .single()

                ScreenErrorManager.showErrorScreen()
                if (moviesList.isNotEmpty()) {
                    _popularMoviesListData.value = moviesList
                    MoviesState.Success(moviesList)
                } else {
                    ScreenErrorManager.showErrorScreen()
                }
            } catch (e: Exception) {
                ScreenErrorManager.showErrorScreen()
            } finally {
                ScreenLoadingManager.hideLoading()
            }

        }
    }

    private fun loadUpcomingMovies() {
        viewModelScope.launch {
            try {
                val moviesList = moviesUseCase
                    .getUpcomingMovies()
                    .flowOn(Dispatchers.IO)
                    .single()

                if (moviesList.isNotEmpty()) {
                    _upcomingMoviesListData.value = moviesList
                    MoviesState.Success(moviesList)
                } else {
                    ScreenErrorManager.showErrorScreen()
                }
            } catch (e: Exception) {
                ScreenErrorManager.showErrorScreen()
            } finally {
                ScreenLoadingManager.hideLoading()
            }

        }
    }
}