package com.example.demokotlin.auth

import android.content.Context
import android.content.Intent
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.sharp.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.demokotlin.ui.theme.AppBackground
import com.example.demokotlin.ui.theme.GradientButton
import com.example.demokotlin.ui.theme.TextArea

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppBackground {
                LoginFormPage()
            }
        }
    }
}

@Composable
fun LoginFormPage() {
    Scaffold(modifier = Modifier.fillMaxSize(), containerColor = Color.Transparent) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            val context = LocalContext.current

            Column(modifier = Modifier.padding(vertical = 60.dp)) {
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
                var email by remember { mutableStateOf("") }
                TextArea(value = email, onValueChange = { newText -> email = newText }, modifier = Modifier.fillMaxWidth(), label = "Email", icon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email Icon"
                    )},
                )
                var password by remember { mutableStateOf("") }
                TextArea(value = password, onValueChange = {newText -> password = newText}, modifier = Modifier.fillMaxWidth(), label = "Password", icon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Password Icon"
                    )},
                    visualTransformation = PasswordVisualTransformation(),
                )
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = { navigateToForgotPassword(context) }), contentAlignment = Alignment.Center) {
                    Text(text = "J'ai oublié mon mot de passe !", textAlign = TextAlign.Center, style = TextStyle(textDecoration = TextDecoration.Underline))
                }
                GradientButton(onClick = {}, text = "Se connecter", modifier = Modifier.fillMaxWidth())
                Column(modifier = Modifier.padding(vertical = 160.dp)) {
                    Text(text= "Toujours pas inscris ?!", textAlign = TextAlign.Center, modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp))
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .clickable(onClick = { navigateToSignUpActivity(context) }), contentAlignment = Alignment.Center) {
                        Text(text = "S'inscire", textAlign = TextAlign.Center, style = TextStyle(textDecoration = TextDecoration.Underline))
                    }
                }
            }
        }
    }
}

fun navigateToSignUpActivity(context: Context) {
    context.startActivity(Intent(context, SignupActivity::class.java))
}

fun navigateToForgotPassword(context: Context) {
    context.startActivity(Intent(context, ForgotPasswordActivity::class.java))
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    AppBackground {
        LoginFormPage()
    }
}