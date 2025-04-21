package com.diogogr85.moviesplay.domain.presentation.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diogogr85.moviesplay.domain.entity.Movie
import com.diogogr85.moviesplay.domain.usecase.movies.MoviesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch

class MoviesViewModel(private val moviesUseCase: MoviesUseCase): ViewModel() {

    private var _moviesListState = MutableLiveData<MoviesState>(MoviesState.Loading)
    val moviesListState: LiveData<MoviesState> = _moviesListState

    private var _popularMoviesListData = MutableLiveData<List<Movie>>()
    val popularMoviesListData: LiveData<List<Movie>> = _popularMoviesListData

    private var _upcomingMoviesListData = MutableLiveData<List<Movie>>()
    val upcomingMoviesListData: LiveData<List<Movie>> = _upcomingMoviesListData

    init {
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

                if (moviesList.isNotEmpty()) {
                    _popularMoviesListData.value = moviesList
                    MoviesState.Success(moviesList)
                } else {
                    _moviesListState.value = MoviesState.Error("No movies found")
                }
            } catch (e: Exception) {
                _moviesListState.value = MoviesState.Error(e.message.toString())
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
                    _moviesListState.value = MoviesState.Error("No movies found")
                }
            } catch (e: Exception) {
                _moviesListState.value = MoviesState.Error(e.message.toString())
            }

        }
    }
}