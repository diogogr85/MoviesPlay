package com.diogogr85.moviesplay.data.network.movies

import com.diogogr85.moviesplay.data.models.MoviesApiResult
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface MoviesService {
    @GET("movie/popular")
    suspend fun getPopularMovies(@QueryMap params: Map<String, String>): MoviesApiResult

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@QueryMap params: Map<String, String>): MoviesApiResult
}