package com.diogogr85.moviesplay.domain.presentation.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diogogr85.moviesplay.domain.usecase.movies.MoviesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch

class MoviesViewModel(private val moviesUseCase: MoviesUseCase): ViewModel() {

    private var _moviesListState = MutableLiveData<MoviesState>(MoviesState.Loading)
    val moviesListState: LiveData<MoviesState> = _moviesListState

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            try {
                val moviesList = moviesUseCase
                    .getMovies()
                    .flowOn(Dispatchers.IO)
                    .single()

                _moviesListState.value =
                    if (moviesList.isNotEmpty()) MoviesState.Success(moviesList)
                    else MoviesState.Error("No movies found")
            } catch (e: Exception) {
                _moviesListState.value = MoviesState.Error(e.message.toString())
            }

        }
    }
}