package com.diogogr85.moviesplay.domain.presentation.movies

import com.diogogr85.moviesplay.data.models.Movie

sealed class MoviesState {
    data object Loading : MoviesState()
    data class Success(val users: List<Movie>) : MoviesState()
    data class Error(val message: String) : MoviesState()
}
