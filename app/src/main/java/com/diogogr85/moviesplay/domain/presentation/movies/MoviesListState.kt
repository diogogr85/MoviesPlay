package com.diogogr85.moviesplay.domain.presentation.movies

import com.diogogr85.moviesplay.domain.entity.Movie

sealed class MoviesState {
    data object Loading : MoviesState()
    data class Success(val movies: List<Movie>) : MoviesState()
    data class Error(val message: String) : MoviesState()
}
