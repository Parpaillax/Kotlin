package com.example.demokotlin.api

import com.example.demokotlin.movie.viewmodel.Movie
import retrofit2.http.GET
import retrofit2.http.Url

interface MovieApi {
    @GET // Remplace par l'endpoint réel
    suspend fun getMovies(@Url url: String): List<Movie> // Retourne une liste d'objets Movie
}
