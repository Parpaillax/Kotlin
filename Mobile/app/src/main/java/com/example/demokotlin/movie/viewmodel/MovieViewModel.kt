package com.example.demokotlin.movie.viewmodel

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.demokotlin.api.MovieApi
import com.example.demokotlin.api.RetrofitInstance
import com.example.demokotlin.auth.viewmodel.SettingsDataStore
import com.example.demokotlin.movie.MovieActivity
import com.example.demokotlin.movie.MovieEditActivity
import com.example.demokotlin.movie.MovieIdActivity
import kotlinx.coroutines.launch

data class Movie(
    var customId: Int = 0,
    var title: String = "",
    var description: String = "",
    var duration: Int = 0,
    var year: Int = 0,
)

class MovieViewModel : ViewModel() {

    private val movieApi = RetrofitInstance.retrofit.create(MovieApi::class.java)

    val moviesList = mutableStateOf<List<Movie>>(emptyList())
    val movieObj = mutableStateOf(Movie())

//    Api CALL
    fun fetchMovies() {
        viewModelScope.launch {
            try {
                val movies = movieApi.getMovies("movies")
                moviesList.value = movies // Met à jour la liste des films
            } catch (e: Exception) {
                e.printStackTrace() // Gère les erreurs ici
            }
        }
    }

    fun fetchOneMovie(id: Int) {
        viewModelScope.launch {
            try {
                val movie = movieApi.getMovieById(id)
                movieObj.value = movie
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun editOneMovie(id: Int, movie: Movie) {
        viewModelScope.launch {
            try {
                movieApi.editMovie(id, movie)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun addOneMovie(movie: Movie, navController: NavController) {
        viewModelScope.launch {
            try {
                movieApi.addMovie(movie)
                navController.navigate("movies")
            } catch(e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun deleteMovieId(id: Int) {
        viewModelScope.launch {
            try {
                movieApi.deleteMovie(id)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

//    Function to get Composable
    fun goToMovieId(context: Context, id: Int) {
        val intent = Intent(context, MovieIdActivity::class.java).apply {
            putExtra("MOVIE_ID", id)
        }
        context.startActivity(intent)
    }

    fun goToEditMovie(context: Context, id: Int) {
        val intent = Intent(context, MovieEditActivity::class.java).apply {
            putExtra("MOVIE_ID", id)
        }
        context.startActivity(intent)
    }

    fun navigateToMoviesList(context: Context) {
        context.startActivity(Intent(context, MovieActivity::class.java))
    }
}