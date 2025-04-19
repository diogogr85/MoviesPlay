package com.diogogr85.moviesplay.data.network.movies

import com.diogogr85.moviesplay.data.models.Movie
import retrofit2.http.GET

interface MoviesService {
    @GET("discover/movie?include_adult=false&include_video=false&language=pt-BR&page=1&sort_by=popularity.desc")
    suspend fun getMovies(): List<Movie>
}