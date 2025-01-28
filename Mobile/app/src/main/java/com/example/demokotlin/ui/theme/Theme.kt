package com.example.demokotlin.ui.theme

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.demokotlin.movie.viewmodel.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

val DarkColorPalette = darkColorScheme(
    primary = Color(0xFF1E88E5),
    secondary = Color(0xFF43A047),
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White,
)

@Composable
fun AppBackground(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF785C65), // Brun-violacé
                        Color(0xFF765A5E), // Rouge-brun doux
                        Color(0xFF6C5054)  // Sombre
                    )
                )
            )
    ) {
        content()
    }
}

@Composable
fun GradientButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String
) {
    Box(
        modifier = modifier
            .padding(vertical = 8.dp)
            .height(48.dp)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFFf7971e), // Orange chaud
                        Color(0xFFff5858)  // Rouge chaud
                    )
                ),
                shape = MaterialTheme.shapes.medium
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.White,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(onMenuClick: () -> Unit = {}) {
    TopAppBar(
        title = { Text("", color = Color.Black) },
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu",
                modifier = Modifier.clickable { onMenuClick() },
                tint = Color.Black
            )
        },
        colors = TopAppBarColors(containerColor = Color.Transparent, scrolledContainerColor = Color.Transparent, titleContentColor = Color.Transparent, navigationIconContentColor = Color.Transparent, actionIconContentColor = Color.Transparent)
    )
}

@Composable
fun TextArea(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String,
    icon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    OutlinedTextField(
        value = value, onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        placeholder = { Text(label) },
        leadingIcon = icon,
        visualTransformation = visualTransformation,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogBox(
    message: String,
    onDismiss: () -> Unit,
    modifier: Modifier
) {
    BasicAlertDialog(
        content = { Text(
            message, modifier = modifier,
            style = TextStyle(Color.Red, fontSize = 18.sp, textDecoration = TextDecoration.Underline)
        )},
        onDismissRequest = onDismiss
    )
}

@Composable
fun MovieItemBox(
    movie: Movie,
    onSee : () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp).border(width = 1.dp, shape = RoundedCornerShape(10.dp), color = Color.Blue).padding(8.dp)) {
        Text(
            text = movie.title,
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = "Synopsis: ${movie.description}",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(
                text = "Durée: ${movie.duration} minutes / ",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                textAlign = TextAlign.Center,
            )
            Text(
                text = "Année: ${movie.year}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                textAlign = TextAlign.Center,
            )
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            GradientButton(onClick = onSee, text = "Voir")
            GradientButton(onClick = onEdit, text = "Editer")
            GradientButton(onClick = onDelete, text = "Supprimer")
        }
    }
}

@Composable
fun MenuBox(
    isAuthenticated: Boolean,
    onNavigate: (String) -> Unit,
    onLogout: suspend () -> Unit
) {
    Column(
        modifier = Modifier
            .border(1.dp, Color.Gray)
            .background(Color.LightGray)
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        if (isAuthenticated) {
            Text(
                text = "Ajouter un film",
                modifier = Modifier.clickable { onNavigate("movie_add") },
                color = Color.Blue
            )
            Text(
                text = "Liste des films",
                modifier = Modifier.clickable { onNavigate("movies") },
                color = Color.Blue
            )
            Text(
                text = "Se déconnecter",
                modifier = Modifier.clickable {
                    CoroutineScope(Dispatchers.Main).launch {
                        onLogout()
                    } },
                color = Color.Red
            )
        } else {
            Text(
                text = "Se connecter",
                modifier = Modifier.clickable { onNavigate("login") },
                color = Color.Blue
            )
            Text(
                text = "S'inscrire",
                modifier = Modifier.clickable { onNavigate("signup") },
                color = Color.Blue
            )
        }
    }
}
