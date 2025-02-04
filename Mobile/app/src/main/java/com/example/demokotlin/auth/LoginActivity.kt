package com.example.demokotlin.auth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.sharp.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.demokotlin.auth.viewmodel.AuthViewModel
import com.example.demokotlin.ui.theme.AppBackground
import com.example.demokotlin.ui.theme.DialogBox
import com.example.demokotlin.ui.theme.GradientButton
import com.example.demokotlin.ui.theme.TextArea

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            AppBackground {
                LoginFormPage(viewModel = AuthViewModel(), navController = navController)
            }
        }
     }
}

@Composable
fun LoginFormPage(viewModel: AuthViewModel, navController: NavController) {
    Scaffold(modifier = Modifier.fillMaxSize(), containerColor = Color.Transparent) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            val context = LocalContext.current
            Column(modifier = Modifier.padding(vertical = 45.dp)) {
                Icon(
                    imageVector = Icons.Sharp.AccountCircle,
                    contentDescription = "Login Icon",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp)
                        .width(90.dp),
                )
                Text(
                    text = "Veuillez faire attention à vos informations de connexions",
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                TextArea(value = viewModel.cred.value.email, onValueChange = { viewModel.updateEmailCred(it) }, modifier = Modifier.fillMaxWidth(), label = "Email", icon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email Icon"
                    )},
                )
                TextArea(value = viewModel.cred.value.password, onValueChange = {viewModel.updatePasswordCred(it)}, modifier = Modifier.fillMaxWidth(), label = "Password", icon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Password Icon"
                    )},
                    visualTransformation = PasswordVisualTransformation(),
                )
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = { navController.navigate("movies") }), contentAlignment = Alignment.Center) {
                    Text(text = "J'ai oublié mon mot de passe !", textAlign = TextAlign.Center, style = TextStyle(textDecoration = TextDecoration.Underline))
                }
                GradientButton(onClick = {viewModel.onLoginClicked(context, viewModel.cred.value, navController) }, text = "Se connecter", modifier = Modifier.fillMaxWidth())
                GradientButton(onClick = {navController.navigate("signup")}, text="S'inscrire", modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp))
                if (viewModel.showDialog.value) {
                    DialogBox(
                        message = viewModel.dialogMessage.value,
                        onDismiss = { viewModel.showDialog.value = false },
                        modifier = Modifier.padding(top = 120.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    val navController = rememberNavController()
    AppBackground {
        LoginFormPage(viewModel = AuthViewModel(), navController = navController)
    }
}