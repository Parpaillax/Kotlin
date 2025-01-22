package com.example.demokotlin.movie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.demokotlin.movie.viewmodel.MovieViewModel
import com.example.demokotlin.ui.theme.AppBackground
import com.example.demokotlin.ui.theme.MovieItemBox

class MovieActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppBackground {
                MovieFormPage(viewModel = MovieViewModel())
            }
        }
    }
}

@Composable
fun MovieFormPage(viewModel: MovieViewModel) {
    LaunchedEffect(Unit) {
        viewModel.fetchMovies()
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
                Text(
                    text = "Liste des films accessible sur l'application",
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
            LazyColumn(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(viewModel.moviesList.value) { movie ->
                    MovieItemBox(movie = movie)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MoviePreview() {
    AppBackground {
        MovieFormPage(viewModel = MovieViewModel())
    }
}