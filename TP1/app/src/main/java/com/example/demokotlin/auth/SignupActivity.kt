package com.example.demokotlin.auth

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.demokotlin.ui.theme.AppBackground
import com.example.demokotlin.ui.theme.GradientButton
import com.example.demokotlin.ui.theme.TextArea

class SignupActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppBackground {
                SignupFormPage()
            }
        }
    }
}

@Composable
fun SignupFormPage() {
    Scaffold(modifier = Modifier.fillMaxSize(), containerColor = Color.Transparent) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Column(modifier = Modifier.padding(40.dp)) {
                Icon(
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = "Signup Icon",
                    modifier = Modifier.fillMaxWidth().height(90.dp).width(90.dp),
                )
                Text(
                    text = "Bienvenue sur cette nouvelle application !",
                    fontSize = 12.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                var pseudo by remember { mutableStateOf("") }
                TextArea(value = pseudo, onValueChange = {newText -> pseudo = newText}, modifier = Modifier.fillMaxWidth(), label = "Pseudo", icon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Nickname Icon"
                    )
                })
                var email by remember { mutableStateOf("") }
                TextArea(value = email, onValueChange = {newText -> email = newText}, modifier = Modifier.fillMaxWidth(), label = "Email", icon = {
                    Icon(
                        imageVector = Icons.Default.MailOutline,
                        contentDescription = "Nickname Icon"
                    )
                })
                var password by remember { mutableStateOf("") }
                TextArea(value = password, onValueChange = {newText -> password = newText}, modifier = Modifier.fillMaxWidth(), label = "Password", icon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Password Icon"
                    )},
                    visualTransformation = PasswordVisualTransformation(),
                    )
                var confirmPassword by remember { mutableStateOf("") }
                TextArea(value = confirmPassword, onValueChange = {newText -> confirmPassword = newText}, modifier = Modifier.fillMaxWidth(), label = "Password Confirmation", icon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Password Icon"
                    )},
                    visualTransformation = PasswordVisualTransformation(),
                    )
                var cityCode by remember { mutableStateOf("") }
                TextArea(value = cityCode, onValueChange = {newText -> cityCode = newText}, modifier = Modifier.fillMaxWidth(), label = "City Code", icon = {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "CityCode Icon"
                    )
                })
                var city by remember { mutableStateOf("") }
                TextArea(value = city, onValueChange = {newText -> city = newText}, modifier = Modifier.fillMaxWidth(), label = "City", icon = {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "City Icon"
                    )
                })
                var phoneNumber by remember { mutableStateOf("") }
                TextArea(value = phoneNumber, onValueChange = {newtext -> phoneNumber = newtext}, modifier = Modifier.fillMaxWidth(), label = "Phone Number", icon = {
                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = "PhoneNumber Icon"
                    )
                })
                GradientButton(onClick = {}, text = "S'inscrire", modifier = Modifier.fillMaxWidth())
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignupPreview() {
    AppBackground {
        SignupFormPage()
    }
}