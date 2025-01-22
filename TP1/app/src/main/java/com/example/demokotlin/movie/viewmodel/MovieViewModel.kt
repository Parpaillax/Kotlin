package com.example.demokotlin.movie.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demokotlin.api.MovieApi
import com.example.demokotlin.api.RetrofitInstance
import kotlinx.coroutines.launch

data class Movie(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val duration: Int = 0,
    val year: Int = 0,
)

class MovieViewModel : ViewModel() {

    private val movieApi = RetrofitInstance.retrofit.create(MovieApi::class.java)

    val moviesList = mutableStateOf<List<Movie>>(emptyList())

    fun fetchMovies() {
        viewModelScope.launch {
            try {
                val movies = movieApi.getMovies("movies.json")
                moviesList.value = movies // Met à jour la liste des films
            } catch (e: Exception) {
                e.printStackTrace() // Gère les erreurs ici
            }
        }
    }
}