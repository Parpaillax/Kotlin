package com.example.demokotlin

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.demokotlin.auth.ForgotPasswordFormPage
import com.example.demokotlin.auth.LoginFormPage
import com.example.demokotlin.auth.SignupFormPage
import com.example.demokotlin.auth.viewmodel.AuthViewModel
import com.example.demokotlin.auth.viewmodel.SettingsDataStore
import com.example.demokotlin.movie.MovieAddFormPage
import com.example.demokotlin.movie.MovieEditFormPage
import com.example.demokotlin.movie.MovieFormPage
import com.example.demokotlin.movie.MovieIdFormPage
import com.example.demokotlin.movie.viewmodel.MovieViewModel
import com.example.demokotlin.ui.theme.AppBackground
import com.example.demokotlin.ui.theme.AppTopBar
import com.example.demokotlin.ui.theme.MenuBox
import kotlinx.coroutines.launch

@Composable
fun MainNavHost() {
    val navController = rememberNavController()
    val displayMenu = remember { mutableStateOf(false) }
    val context = LocalContext.current
    val settingsDataStore = remember { SettingsDataStore(context) }
    val isAuthenticated by settingsDataStore.isAuthenticatedFlow.collectAsState(initial = false)
    AppBackground {
        Scaffold(
            topBar = {
                AppTopBar(onMenuClick = {displayMenu.value = !displayMenu.value})
            },
            containerColor = Color.Transparent
        ) {innerPadding->
            Box(modifier = Modifier.padding(innerPadding)) {
                NavHost(
                    navController = navController,
                    startDestination = "login",
                ) {
                    composable("login") {
                        LoginFormPage(viewModel = AuthViewModel(), navController = navController)
                    }
                    composable("signup") {
                        SignupFormPage(viewModel = AuthViewModel())
                    }
                    composable("forgot_password") {
                        ForgotPasswordFormPage(viewModel = AuthViewModel())
                    }
                    composable("movie_add") {
                        MovieAddFormPage(viewModel = MovieViewModel(), navController = navController)
                    }
                    composable("movies") {
                        MovieFormPage(viewModel = MovieViewModel(), navController = navController)
                    }
                    composable("movie_id/{movieId}", arguments = listOf(
                        navArgument("movieId") {
                            type = NavType.IntType
                        }
                    )) { backStackEntry ->
                        val movieId = backStackEntry.arguments?.getInt("movieId") ?: 0
                        MovieIdFormPage(movieId = movieId, viewModel = MovieViewModel())
                    }
                    composable("movie_edit/{movieId}", arguments = listOf(
                        navArgument("movieId") {
                            type = NavType.IntType
                        }
                    )) { backStackEntry ->
                        val movieId = backStackEntry.arguments?.getInt("movieId") ?: 0
                        MovieEditFormPage(movieId = movieId, viewModel = MovieViewModel())
                    }
                }
                if (displayMenu.value) {
                    MenuBox(
                        isAuthenticated = isAuthenticated,
                        onNavigate = { destination ->
                            navController.navigate(destination)
                        },
                        onLogout = {
                            settingsDataStore.clearToken() // Déconnecter l'utilisateur
                            navController.navigate("login") {
                                popUpTo("login") { inclusive = true }
                            }
                        }
                    )
                }
            }
        }
    }
}
