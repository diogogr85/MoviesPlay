package com.diogogr85.moviesplay.data.network.movies

import com.diogogr85.moviesplay.data.models.MoviesApiResult
import retrofit2.http.GET

interface MoviesService {
    @GET("movie/popular?language=pt-BR&page=1")
    suspend fun getPopularMovies(): MoviesApiResult

    @GET("movie/upcoming?language=pt-BR&page=1")
    suspend fun getUpcomingMovies(): MoviesApiResult
}