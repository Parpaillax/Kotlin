package com.example.demokotlin.movie

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.PlayArrow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.demokotlin.auth.LoginActivity
import com.example.demokotlin.auth.viewmodel.SettingsDataStore
import com.example.demokotlin.movie.viewmodel.MovieViewModel
import com.example.demokotlin.ui.theme.AppBackground
import com.example.demokotlin.ui.theme.MovieItemBox
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class MovieActivity : ComponentActivity() {
    private lateinit var dataStoreManager: SettingsDataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataStoreManager = SettingsDataStore(this)
        val isAuthenticated = runBlocking {
            dataStoreManager.isAuthenticatedFlow.first()
        }
        if (!isAuthenticated) {
            // Redirige vers l'écran de connexion
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() // Ferme l'activité actuelle
        }
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            AppBackground {
                MovieFormPage(viewModel = MovieViewModel(), navController = navController)
            }
        }
    }
}

@Composable
fun MovieFormPage(viewModel: MovieViewModel, navController: NavController) {
    val isLoading = remember { mutableStateOf(true) }
    LaunchedEffect(Unit) {
        viewModel.fetchMovies()
        isLoading.value = false
    }
    val moviesList = remember { viewModel.moviesList }
    AppBackground {
        Scaffold(modifier = Modifier.fillMaxSize(), containerColor = Color.Transparent) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                val context = LocalContext.current
                Column {
                    Icon(
                        imageVector = Icons.Sharp.PlayArrow,
                        contentDescription = "Movie Icon",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(90.dp)
                            .width(90.dp),
                    )
                    Text(
                        text = "Liste des films accessible sur l'application",
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
                if (isLoading.value) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    LazyColumn(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(moviesList.value) { movie ->
                            MovieItemBox(
                                movie = movie,
                                onSee = {navController.navigate("movie_id/${movie.customId}")},
                                onEdit = {navController.navigate("movie_edit/${movie.customId}")},
                                onDelete = {viewModel.deleteMovieId(movie.customId); navController.navigate("movies")}
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MoviePreview() {
    val navController = rememberNavController()
    AppBackground {
        MovieFormPage(viewModel = MovieViewModel(), navController = navController)
    }
}