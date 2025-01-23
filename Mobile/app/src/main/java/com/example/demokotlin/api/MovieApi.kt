package com.example.demokotlin.api

import com.example.demokotlin.movie.viewmodel.Movie
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Url

interface MovieApi {
    @GET // Remplace par l'endpoint réel
    suspend fun getMovies(@Url url: String): List<Movie> // Retourne une liste d'objets Movie

    @GET("movie/{id}")
    suspend fun getMovieById(@Path("id") id: Int): Movie

    @PATCH("movie/edit/{id}")
    suspend fun editMovie(@Path("id") id: Int, @Body movie: Movie): Movie

    @DELETE("movie/delete/{id}")
    suspend fun deleteMovie(@Path("id") id: Int)

    @POST("movies")
    suspend fun saveMovie(@Body movie: Movie): Response<Movie>
}
