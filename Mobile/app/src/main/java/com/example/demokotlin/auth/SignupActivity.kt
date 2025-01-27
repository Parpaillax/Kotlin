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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.demokotlin.auth.viewmodel.AuthViewModel
import com.example.demokotlin.ui.theme.AppBackground
import com.example.demokotlin.ui.theme.DialogBox
import com.example.demokotlin.ui.theme.GradientButton
import com.example.demokotlin.ui.theme.TextArea

class SignupActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppBackground {
                SignupFormPage(viewModel = AuthViewModel())
            }
        }
    }
}

@Composable
fun SignupFormPage(viewModel: AuthViewModel) {
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
                TextArea(value = viewModel.user.value.pseudo, onValueChange = {viewModel.updatePseudo(it)}, modifier = Modifier.fillMaxWidth(), label = "Pseudo", icon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Nickname Icon"
                    )
                })
                TextArea(value = viewModel.user.value.email, onValueChange = {viewModel.updateEmail(it)}, modifier = Modifier.fillMaxWidth(), label = "Email", icon = {
                    Icon(
                        imageVector = Icons.Default.MailOutline,
                        contentDescription = "Nickname Icon"
                    )
                })
                TextArea(value = viewModel.user.value.password, onValueChange = {viewModel.updatePassword(it)}, modifier = Modifier.fillMaxWidth(), label = "Password", icon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Password Icon"
                    )},
                    visualTransformation = PasswordVisualTransformation(),
                    )
                TextArea(value = viewModel.confirmPassword.value, onValueChange = {newText -> viewModel.confirmPassword.value = newText}, modifier = Modifier.fillMaxWidth(), label = "Password Confirmation", icon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Password Icon"
                    )},
                    visualTransformation = PasswordVisualTransformation(),
                    )
                TextArea(value = viewModel.user.value.cityCode, onValueChange = {viewModel.updateCityCode(it)}, modifier = Modifier.fillMaxWidth(), label = "City Code", icon = {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "CityCode Icon"
                    )
                })
                TextArea(value = viewModel.user.value.city, onValueChange = {viewModel.updateCity(it)}, modifier = Modifier.fillMaxWidth(), label = "City", icon = {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "City Icon"
                    )
                })
                TextArea(value = viewModel.user.value.phone, onValueChange = {viewModel.updatePhone(it)}, modifier = Modifier.fillMaxWidth(), label = "Phone Number", icon = {
                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = "PhoneNumber Icon"
                    )
                })
                GradientButton(onClick = {viewModel.onSignupCheck()}, text = "S'inscrire", modifier = Modifier.fillMaxWidth())
                if (viewModel.showDialog.value) {
                    DialogBox(message = viewModel.dialogMessage.value, onDismiss = { viewModel.showDialog.value = false }, modifier = Modifier.padding(top = 40.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignupPreview() {
    AppBackground {
        SignupFormPage(viewModel = AuthViewModel())
    }
}