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

    init {
        loadPopularMovies()
    }

    private fun loadPopularMovies() {
        viewModelScope.launch {
            try {
                val moviesList = moviesUseCase
                    .getPopularMovies()
                    .flowOn(Dispatchers.IO)
                    .single()

                _moviesListState.value =
                    if (moviesList.isNotEmpty()) {
                        _popularMoviesListData.value = moviesList
                        MoviesState.Success(moviesList)
                    } else {
                        MoviesState.Error("No movies found")
                    }
            } catch (e: Exception) {
                _moviesListState.value = MoviesState.Error(e.message.toString())
            }

        }
    }
}