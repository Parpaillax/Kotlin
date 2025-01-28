package com.example.demokotlin.movie

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.PlayArrow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.demokotlin.auth.LoginActivity
import com.example.demokotlin.auth.viewmodel.SettingsDataStore
import com.example.demokotlin.movie.viewmodel.MovieViewModel
import com.example.demokotlin.ui.theme.AppBackground

class MovieIdActivity : ComponentActivity() {
    private lateinit var dataStoreManager: SettingsDataStore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataStoreManager = SettingsDataStore(this)
        if (!dataStoreManager.isAuthenticated()) {
            // Redirige vers l'écran de connexion
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() // Ferme l'activité actuelle
        }
        val movieId = intent.getIntExtra("MOVIE_ID", -1)
        enableEdgeToEdge()
        setContent {
            AppBackground {
                MovieIdFormPage(movieId = movieId, viewModel = MovieViewModel())
            }
        }
    }
}

@Composable
fun MovieIdFormPage(movieId: Int, viewModel: MovieViewModel) {
    val movie = viewModel.movieObj.value
    val isLoading = remember { mutableStateOf(true) }
    LaunchedEffect(movieId) {
        viewModel.fetchOneMovie(movieId)
        isLoading.value = false
    }
    Scaffold(modifier = Modifier.fillMaxSize(), containerColor = Color.Transparent) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Column(modifier = Modifier.padding(vertical = 20.dp)) {
                Icon(
                    imageVector = Icons.Sharp.PlayArrow,
                    contentDescription = "Movie Icon",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp)
                        .width(90.dp),
                )
            }
            if (isLoading.value) {
                // Afficher un indicateur de chargement
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                // Afficher les détails du film
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Text(text = "Title: ${movie.title}", style = MaterialTheme.typography.titleLarge)
                    Text(text = "Description: ${movie.description}", style = MaterialTheme.typography.bodyMedium)
                    Text(text = "Duration: ${movie.duration} min", style = MaterialTheme.typography.bodyMedium)
                    Text(text = "Year: ${movie.year}", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieIdPreview() {
    AppBackground {
        MovieIdFormPage(movieId = 0, viewModel = MovieViewModel())
    }
}